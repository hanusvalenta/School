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

int main() {
    const string in_directory = "Files";
    const string out_directory = "Out";

    vector<filesystem::path> files_in_dir;
    try {
        if (filesystem::exists(in_directory) && filesystem::is_directory(in_directory)) {
            for (const auto& entry : filesystem::directory_iterator(in_directory)) {
                if (entry.is_regular_file()) {
                    files_in_dir.push_back(entry.path());
                }
            }
        }
    } catch (const filesystem::filesystem_error& e) {
        cerr << "Error accessing directory: " << e.what() << endl;
        return 1;
    }

    if (files_in_dir.empty()) {
        cout << "No files found in the '" << in_directory << "' subfolder. Please add some files and try again." << endl;
        return 1;
    }

    cout << "Please pick a file to compress:" << endl;
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

    string compressed_content = rle_compress(file_content);

    cout << "\nOriginal content size: " << file_content.length() << " bytes" << endl;
    cout << "Compressed content size: " << compressed_content.length() << " bytes" << endl;
    cout << "Compressed data: " << compressed_content << endl;

    try {
        filesystem::create_directory(out_directory);
    } catch (const filesystem::filesystem_error& e) {
        cerr << "Error creating output directory: " << e.what() << endl;
        return 1;
    }

    filesystem::path out_file_path = filesystem::path(out_directory) / selected_file_path.filename();
    out_file_path += ".rle";

    ofstream outFile(out_file_path);
    if (!outFile.is_open()) {
        cerr << "Error: Could not create output file " << out_file_path << endl;
        return 1;
    }
    outFile << compressed_content;
    outFile.close();

    cout << "\nSuccessfully compressed and saved to " << out_file_path << endl;

    return 0;
}