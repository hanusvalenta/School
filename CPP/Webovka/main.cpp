#include <algorithm>
#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#include <shellapi.h>

using namespace std;

int main() {
    string jmeno;
    string value;
    int radky;
    int sloupce;

    srand (time(NULL));

    cout << "Zadej svoje jmeno \n";
    cin >> jmeno;

    cout << "Zadej pocet sloupcu \n";
    cin >> sloupce;

    cout << "Zadej pocet radku \n";
    cin >> radky;

    ofstream file;

    file.open("index.html");

    if (!file.is_open())
    {
        cout << "Web se nepovedlo udělat" << endl;

        return 1;
    }

    file <<
        "<html>" << endl <<
        "<head>" << endl <<
        "<title>" << jmeno << " " << sloupce << " " << radky << "</title>" << endl <<
        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css\" integrity=\"sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls\" crossorigin=\"anonymous\">" << endl <<
        "</head>" << endl <<
            "<body style=\"display: flex; align-items: center; justify-content: center;\" >" << endl <<
        "<table class=\"pure-table\">" << endl;

        for (int i = 1; i < radky + 1; i++) {
            file << "<tr>" << endl;
            for (int j = 1; j < sloupce + 1; j++) {
                file << "<td>" << endl;
                cout << "Zadej hodnotu pro radek " << i << " sloupec " << j << endl;
                cin >> value;
                file << value << endl;
                file << "</td>" << endl;
            }
            file << "</tr>" << endl;
        }

    file <<
        "</table>" << endl <<
        "</html>" << endl;

    file.close();

    cout << "Web zalozen otviram" << endl;

    ShellExecute(NULL, "open", "index.html", NULL, NULL, SW_SHOWNORMAL);

    return 0;
}