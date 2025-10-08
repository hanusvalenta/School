#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main() {
    string Veta = "Epstein se nezabil";
    string ZobrazenaVeta(Veta.length(), '_');
    string UhodnutePismena = "";
    bool Uhodl = false;
    int PocetPokusu = 10;

    for (int i = 0; i < Veta.length(); ++i) {
        if (Veta[i] == ' ') {
            ZobrazenaVeta[i] = ' ';
        }
    }

    while (!Uhodl) {
        cout << "Veta: " << ZobrazenaVeta << endl;
        cout << "Hadej pismeno" << endl;
        char Pismeno;
        cout << "Hadane pismena: " << UhodnutePismena << endl;
        cout << "Mas jeste: " << PocetPokusu << " pokusu" << endl;

        cin >> Pismeno;

        for (int i = 0; i < Veta.length(); i++) {
            if (Pismeno == UhodnutePismena[i]) {
                cout << Pismeno << " Si uz hadal" << endl;
                PocetPokusu++;
            }
            else if (Pismeno == Veta[i]) {
                ZobrazenaVeta[i] = Pismeno;
                PocetPokusu++;
            }
            else if (i == Veta.length() - 1) {
                PocetPokusu--;
            }
        }

        UhodnutePismena += Pismeno;

        if (PocetPokusu == 0) {
            cout << "Prohral jsi :c" << endl;
            cout << "Hadane slovo bylo: " << Veta << endl;
            exit(0);
        }

        if (ZobrazenaVeta == Veta) {
            cout << "Gratuluju uhodl jsi :3c" << endl;
            Uhodl = true;
        }
    }
}