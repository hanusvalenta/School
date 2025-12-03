#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <filesystem>

using namespace std;

string RleCompress(const string& data)
{
    if (data.empty())
    {
        return "";
    }

    string compressedData;
    int count = 1;
    char currentChar = data[0];

    for (size_t i = 1; i < data.length(); ++i)
    {
        if (data[i] == currentChar)
        {
            count++;
        }
        else
        {
            compressedData += to_string(count);
            compressedData += currentChar;
            currentChar = data[i];
            count = 1;
        }
    }

    compressedData += to_string(count);
    compressedData += currentChar;

    return compressedData;
}

string RleDecompress(const string& data)
{
    if (data.empty())
    {
        return "";
    }

    string decompressedData;
    string countString;

    for (char c : data)
    {
        if (isdigit(c))
        {
            countString += c;
        }
        else
        {
            if (!countString.empty())
            {
                int count = stoi(countString);
                decompressedData.append(count, c);
                countString.clear();
            }
        }
    }
    return decompressedData;
}

int main(int argc, char* argv[])
{
    filesystem::path exePath = filesystem::path(argv[0]);
    filesystem::path baseDir = exePath.parent_path();

    cout << "Zadej moznost: " << endl;
    cout << "1. Zabalit" << endl;
    cout << "2. Rozbalit" << endl;
    cout << "> ";
    int modeChoice = 0;
    cin >> modeChoice;

    if (cin.fail() || (modeChoice != 1 && modeChoice != 2))
    {
        cout << "Neplatna moznost" << endl;
        return 1;
    }

    bool isCompressing = (modeChoice == 1);

    filesystem::path inDirectory = baseDir / "Files";
    filesystem::path outDirectory = baseDir / "Out";

    vector<filesystem::path> filesInDir;
    try
    {
        if (filesystem::exists(inDirectory) && filesystem::is_directory(inDirectory))
        {
            for (const auto& entry : filesystem::directory_iterator(inDirectory))
            {
                if (entry.is_regular_file())
                {
                    bool isRleComFile = entry.path().extension() == ".minibussy";
                    if (isCompressing && !isRleComFile)
                    {
                        filesInDir.push_back(entry.path());
                    }
                    else if (!isCompressing && isRleComFile)
                    {
                        filesInDir.push_back(entry.path());
                    }
                }
            }
        }
    }
    catch (const filesystem::filesystem_error& e)
    {
        cerr << e.what() << endl;
        return 1;
    }

    if (filesInDir.empty()) {
        cout << "Zadne soubory v: " << inDirectory.string() << endl;
        return 1;
    }

    cout << "\nVyber soubor co " << (isCompressing ? "zabalit" : "rozbalit") << ":" << endl;
    for (size_t i = 0; i < filesInDir.size(); ++i)
    {
        cout << i + 1 << ": " << filesInDir[i].filename().string() << endl;
    }

    int choice = 0;
    cout << "> ";
    cin >> choice;

    filesystem::path selectedFilePath = filesInDir[choice - 1];

    ifstream inFile(selectedFilePath);

    string fileContent((istreambuf_iterator<char>(inFile)), istreambuf_iterator<char>());
    inFile.close();

    try {
        filesystem::create_directory(outDirectory);
    } catch (const filesystem::filesystem_error& e) {
        cerr << e.what() << endl;
        return 1;
    }

    filesystem::path outFile_path;
    string resultContent;

    if (isCompressing) {
        resultContent = RleCompress(fileContent);
        outFile_path = outDirectory / selectedFilePath.filename();
        outFile_path += ".minibussy";
        cout << "\nPuvodni velikost: " << fileContent.length() << " bajtu" << endl;
        cout << "Nova velikost: " << resultContent.length() << " bajtu" << endl;
    } 
    else 
    {
        resultContent = RleDecompress(fileContent);
        filesystem::path originalFilename = selectedFilePath.stem();
        outFile_path = outDirectory / originalFilename;
        cout << "\nPuvodni velikost: " << fileContent.length() << " bajtu" << endl;
        cout << "Nova velikost: " << resultContent.length() << " bajtu" << endl;
    }

    ofstream outFile(outFile_path);
    outFile << resultContent;
    outFile.close();

    cout << (isCompressing ? "Zabaleno" : "Rozbaleno") << " do " << outFile_path.string() << endl;

    return 0;
}