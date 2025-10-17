#include <iostream>
#include <fstream>
#include <string>
using namespace std;


int main() {
    std::cout << R"(
     ________  __  __           
/        |/  |/  |          
$$$$$$$$/ $$/ $$ |  ______  
$$ |__    /  |$$ | /      \ 
$$    |   $$ |$$ |/$$$$$$  |
$$$$$/    $$ |$$ |$$    $$ |
$$ |      $$ |$$ |$$$$$$$$/ 
$$ |      $$ |$$ |$$       |
$$/       $$/ $$/  $$$$$$$/ 
                            
                            
                            
)" << '\n';

    ofstream outFile("text.bussy");

    outFile << "je realny";

    outFile.close();

    string fileContent;

    ifstream inFile("text.bussy");

    getline(inFile, fileContent);

    cout << "Obsah souboru - " << fileContent << endl;

    inFile.close();

    return 0;
}