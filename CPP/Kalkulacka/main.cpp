#include <iostream>
using namespace std;

int main() {
    std::cout << R"(
    ______         _      _        _     _
 |___  /        | |    (_)      (_)   | |
    / / __ _  __| | ___ _    ___ _ ___| | ___     ___  _ __   ___ _ __ __ _  ___ ___
   / / / _` |/ _` |/ _ \ |  / __| / __| |/ _ \   / _ \| '_ \ / _ \ '__/ _` |/ __/ _ \
  / /_| (_| | (_| |  __/ | | (__| \__ \ | (_) | | (_) | |_) |  __/ | | (_| | (_|  __/
 /_____\__,_|\__,_|\___| |  \___|_|___/_|\___/   \___/| .__/ \___|_|  \__,_|\___\___|
                      _/ |                            | |
                     |__/                             |_|
    )" << '\n';
    cout << "1. Suma ciselnych cifer\n";
    cout << "2. kalkulacka\n";
    cout << "3. idk to je uvidi\n";
    int O;
    cin >> O;
    if (O == 1) {
        cout << "Zadej cislo\n";
        int cislo;
        cin >> cislo;
        int celkem;

        while (cislo != 0) {
            celkem += cislo % 10;
            cislo /= 10;
        }

        cout << celkem << endl;
        return 0;

    }
    if (O == 2) {
        cout << "Zadej cislo 1\n";
        int cislo1;
        cin >> cislo1;

        cout << "Zadej operaci\n";
        char operace;
        cin >> operace;

        cout << "Zadej cislo 2\n";
        int cislo2;
        cin >> cislo2;

        switch (operace) {
            case '+':
                cout << cislo1 + cislo2;
                return 0;
            case '-':
                cout << cislo1 - cislo2;
                return 0;
            case '/':
                cout << cislo1 / cislo2;
                return 0;
            case '*':
                cout << cislo1 * cislo2;
                return 0;
            case '%':
                cout << cislo1 % cislo2;
                return 0;
        }

        if (operace != '+' && operace != '-' && operace != '/' && operace != '*' && operace != '%') {
            cout << "Spatna operace" << endl;
        }
    }
    if (O == 3) {
        cout << "Zatím neimplementované\n";
    }
    else if (O >= 4 || O <= 0) {
        cout << "Spatne cislo";
    }

    return 0;
}