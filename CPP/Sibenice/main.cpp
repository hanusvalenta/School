#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <cctype>
using namespace std;

int main() {
    srand(time(0));

    vector<string> Vety = {
        "Epstein se nezabil", "Chemtrails umyslne","Alieni mezi námi", "jedenact zari kontrolovaný výbuch", "Covid laboratorni unik",
        "Media kontrolovana", "Moon landing falesne", "Izrael hladomor taktika", "Vacciny nebezpecne", "Dukazy zniceny",
        "JFK vrazda planovana", "Rwanda genocida hutu", "Iraq valka falešna", "Area padesatjedna UFO", "Scientology tajne dokumenty",
        "Waco ohniva strelba", "Ruby Ridge FBI", "Jonestown masakr", "Raeliani klonovani", "Moonie svatby"
    };

    bool hratZnovu = true;
    while (hratZnovu) {
        string Veta = Vety[rand() % Vety.size()];

        string ZobrazenaVeta(Veta.length(), '_');

        for (int i = 0; i < Veta.length(); ++i) {
            if (Veta[i] == ' ') {
                ZobrazenaVeta[i] = ' ';
            }
        }

        string UhodnutePismena = "";
        bool Uhodl = false;
        int PocetPokusu = 10;

        while (PocetPokusu > 0 && Uhodl == false) {
            cout << "Veta: " << ZobrazenaVeta << endl;
            cout << "Hadej pismeno" << endl;
            cout << "Hadane pismena: " << UhodnutePismena << endl;
            cout << "Mas jeste: " << PocetPokusu << " pokusu" << endl;

            string input;
            cin >> input;

            if (input.length() != 1 || isalpha(input[0]) == false) {
                cout << "Neplatny vstup zadej prosim jenom jedno pismeno" << endl;
                cin.clear();
            }

            char Pismeno = tolower(input[0]);

            if (UhodnutePismena.find(Pismeno) != string::npos) {
                cout << "Pismeno '" << Pismeno << "' jsi uz zkusil" << endl;
                continue;
            }

            UhodnutePismena += Pismeno;
            UhodnutePismena += ' ';

            bool pismenoNalezeno = false;
            for (int i = 0; i < Veta.length(); i++) {
                if (tolower(Veta[i]) == Pismeno) {
                    ZobrazenaVeta[i] = Veta[i];
                    pismenoNalezeno = true;
                }
            }

            if (pismenoNalezeno) {
                cout << "Spravne" << endl;
            } else {
                PocetPokusu--;
            }

            if (ZobrazenaVeta == Veta) {
                Uhodl = true;
            }
        }

        if (Uhodl == true) {
            cout << "Gratuluju, uhodl jsi! :3" << endl;
        } else {
            cout << "Prohral jsi :c" << endl;
            cout << "Hadane slovo bylo: " << Veta << endl;
        }

        cout << "Chces hrat znovu? (ano/ne): ";
        string odpoved;
        cin >> odpoved;

        if (odpoved == "ano" || odpoved == "Ano") {
            hratZnovu = true;
        }
        else {
            hratZnovu = false;
        }
    }

    return 0;
}