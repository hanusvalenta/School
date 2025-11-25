#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <filesystem>

using namespace std;

string RleCompress(const string& data) {
    if (data.empty()) {
        return "";
    }

    string compressed_data;
    int count = 1;
    char current_char = data[0];

    for (size_t i = 1; i < data.length(); ++i) {
        if (data[i] == current_char) {
            count++;
        } else {
            compressed_data += to_string(count);
            compressed_data += current_char;
            current_char = data[i];
            count = 1;
        }
    }

    compressed_data += to_string(count);
    compressed_data += current_char;

    return compressed_data;
}

string RleDecompress(const string& data) {
    if (data.empty()) {
        return "";
    }

    string decompressed_data;
    string count_str;

    for (char c : data) {
        if (isdigit(c)) {
            count_str += c;
        } else {
            if (!count_str.empty()) {
                int count = stoi(count_str);
                decompressed_data.append(count, c);
                count_str.clear();
            }
        }
    }
    return decompressed_data;
}

int main(int argc, char* argv[]) {
    if (argc < 1) {
        cerr << "Error: Could not determine executable path." << endl;
        return 1;
    }
    
    filesystem::path exe_path = filesystem::path(argv[0]);
    filesystem::path base_dir = exe_path.parent_path();

    cout << "Please select an option:" << endl;
    cout << "1: Compress a file" << endl;
    cout << "2: Decompress a file" << endl;
    cout << "> ";
    int mode_choice = 0;
    cin >> mode_choice;

    if (cin.fail() || (mode_choice != 1 && mode_choice != 2)) {
        cout << "Invalid choice. Exiting." << endl;
        return 1;
    }

    bool is_compressing = (mode_choice == 1);

    filesystem::path in_directory = base_dir / "Files";
    filesystem::path out_directory = base_dir / "Out";

    vector<filesystem::path> files_in_dir;
    try {
        if (filesystem::exists(in_directory) && filesystem::is_directory(in_directory)) {
            for (const auto& entry : filesystem::directory_iterator(in_directory)) {
                if (entry.is_regular_file()) {
                    bool is_rle_file = entry.path().extension() == ".minibussy";
                    if (is_compressing && !is_rle_file)
                        {
                        files_in_dir.push_back(entry.path());
                    }
                    else if (!is_compressing && is_rle_file)
                    {
                        files_in_dir.push_back(entry.path());
                    }
                }
            }
        }
    } catch (const filesystem::filesystem_error& e) {
        cerr << "Error accessing directory: " << e.what() << endl;
        return 1;
    }

    if (files_in_dir.empty()) {
        cout << "No files found in the '" << in_directory.string() << "' subfolder. Please add some files and try again." << endl;
        return 1;
    }

    cout << "\nPlease pick a file to " << (is_compressing ? "compress" : "decompress") << ":" << endl;
    for (size_t i = 0; i < files_in_dir.size(); ++i) {
        cout << i + 1 << ": " << files_in_dir[i].filename().string() << endl;
    }

    int choice = 0;
    cout << "> ";
    cin >> choice;

    if (cin.fail() || choice < 1 || choice > files_in_dir.size()) {
        cout << "Invalid choice. Exiting." << endl;
        return 1;
    }

    filesystem::path selected_file_path = files_in_dir[choice - 1];

    ifstream inFile(selected_file_path);
    if (!inFile.is_open()) {
        cerr << "Error: Could not open file " << selected_file_path << endl;
        return 1;
    }
    string file_content((istreambuf_iterator<char>(inFile)), istreambuf_iterator<char>());
    inFile.close();

    try {
        filesystem::create_directory(out_directory);
    } catch (const filesystem::filesystem_error& e) {
        cerr << "Error creating output directory: " << e.what() << endl;
        return 1;
    }

    filesystem::path out_file_path;
    string result_content;

    if (is_compressing) {
        result_content = RleCompress(file_content);
        out_file_path = out_directory / selected_file_path.filename();
        out_file_path += ".minibussy";
        cout << "\nOriginal content size: " << file_content.length() << " bytes" << endl;
        cout << "Compressed content size: " << result_content.length() << " bytes" << endl;
    } 
    else 
    {
        result_content = RleDecompress(file_content);
        filesystem::path original_filename = selected_file_path.stem(); // e.g., file.txt from file.txt.rle
        out_file_path = out_directory / original_filename;
        cout << "\nCompressed content size: " << file_content.length() << " bytes" << endl;
        cout << "Decompressed content size: " << result_content.length() << " bytes" << endl;
    }

    ofstream outFile(out_file_path);
    if (!outFile.is_open()) {
        cerr << "Error: Could not create output file " << out_file_path << endl;
        return 1;
    }
    outFile << result_content;
    outFile.close();

    cout << "\nSuccessfully " << (is_compressing ? "compressed" : "decompressed") << " and saved to " << out_file_path.string() << endl;

    return 0;
}