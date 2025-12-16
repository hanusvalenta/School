#include <iostream>
#include <fstream>
#include <string>
#include <memory>
#include <ctime>
using namespace std;

void prohod(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    cout << R"(
 /$$$$$$$           /$$             /$$                        
| $$__  $$         |__/            | $$                        
| $$  \ $$ /$$$$$$  /$$ /$$$$$$$  /$$$$$$    /$$$$$$   /$$$$$$ 
| $$$$$$$//$$__  $$| $$| $$__  $$|_  $$_/   /$$__  $$ /$$__  $$
| $$____/| $$  \ $$| $$| $$  \ $$  | $$    | $$$$$$$$| $$  \__/
| $$     | $$  | $$| $$| $$  | $$  | $$ /$$| $$_____/| $$      
| $$     |  $$$$$$/| $$| $$  | $$  |  $$$$/|  $$$$$$$| $$      
|__/      \______/ |__/|__/  |__/   \___/   \_______/|__/      
)" << '\n';
    //vypsat
    cout << "Zadej slovo\n";
    string i;
    cin >> i;

    string *j = &i;

    cout << "Slovo '" << *j << "' je ulozeno na adrese " << j << endl << endl;

    //cas
    time_t timer;
    time(&timer);
    cout << "Aktualni casovy timestamp: " << timer << endl << endl;

    //prohodit
    int x = 10, y = 20;
    cout << "Pred: x = " << x << ", y = " << y << endl;
    prohod(&x, &y);
    cout << "Po:   x = " << x << ", y = " << y << endl << endl;

    //pole
    int staticke[5] = {10, 20, 30, 40, 50};
    int* pStat = staticke;
    cout << "Hodnoty: ";
    for(int k = 0; k < 5; k++) {
        cout << *(pStat + k) << " ";
    }
    cout << endl << endl;

    //allokace
    cout << "Zadej velikost pole\n";
    int size;
    cin >> size;
    int* dynamicke = nullptr;
    if (size > 0) {
        dynamicke = new int[size];
        for(int k = 0; k < size; k++) dynamicke[k] = k * 10;
        cout << "Pole alokovano na adrese " << dynamicke << endl;
        cout << "Prvni prvek: " << dynamicke[0] << " Posledni - " << dynamicke[size-1] << endl;
        delete[] dynamicke;
        cout << "Pamet uvolnena" << endl << endl;
    }

    //ja pointer
    unique_ptr<int> chytry(new int(999));
    cout << "Hodnota v unique_ptr: " << *chytry << endl;

    return 0;
}