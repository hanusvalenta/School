#include <iostream>
#include <string>
#include <vector>
#include <ctime>
#include <cctype>
using namespace std;

int main() {
    srand(time(0));

    vector<string> Vety = {
        "Epstein se nezabil", "Chemtrails umyslne","Alieni mezi námi", "jedenact zari kontrolovaný výbuch", "Covid laboratorni unik","Media kontrolovana", "Moon landing falesne", "Izrael hladomor taktika", "Vacciny nebezpecne", "Dukazy zniceny","JFK vrazda planovana", "Rwanda genocida hutu", "Iraq valka falesna", "Area padesatjedna UFO", "Scientology tajne dokumenty","Waco ohniva strelba", "Ruby Ridge FBI", "Jonestown masakr", "Raeliani klonovani", "Moonie svatby"
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

            if (input.length() != 1 || input == "1" || input == "2" || input == "3" || input == "4" || input == "5" || input == "6" || input == "7" || input == "8" || input == "9" || input == "10" || input == "/" || input == "11" || input == "." || input == "." || input == ";" || input == "[" || input == "]") {
                cout << "Neplatny vstup zadej prosim jenom jedno pismeno" << endl;
                cin.clear();
                PocetPokusu++; // tohle tu musi byt jinak mizi pokus i kdyz je spatny vstup
            }

            char Pismeno = tolower(input[0]);

            bool uzHadal = false;
            for (char hadanePismeno : UhodnutePismena) {
                if (hadanePismeno == Pismeno) {
                    uzHadal = true;
                    break;
                }
            }

            if (uzHadal) {
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
                cout << "Nope to ne" << endl;
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