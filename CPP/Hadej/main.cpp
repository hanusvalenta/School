#include <iostream>
#include <ctime>
#include <cstdlib>
#include <windows.h>
using namespace std;

// g++ main.cpp -o myprogram.exe -mwindows -Wl,--subsystem,windows -Wl,--enable-stdcall-fixup -Wl,--image-base,0x400000 -Wl,--major-subsystem-version,5 -Wl,--minor-subsystem-version,1 -Wl,--subsystem,console -Wl,--out-implib,libmyprogram.a -Wl,--export-all-symbols -Wl,--add-stdcall-alias -Wl,--enable-auto-import -Wl,--stack,0x200000 -Wl,--major-link-version,1 -Wl,--minor-link-version,0 -Wl,--major-os-version,5 -Wl,--minor-os-version,1 -Wl,--major-image-version,1 -Wl,--minor-image-version,0 -Wl,--version-info,1,0 -Wl,--output-manifest,myprogram.exe.manifest -Wl,--embed-manifest -Wl,--manifest,manifest.xml

unsigned bounded_rand(unsigned range)
{
    for (unsigned x, r;;)
        if (x = rand(), r = x % range, x - r <= -range)
            return r;
}

int main() {
    cout << R"(  __  __      _ _        _
 |  \/  |_  _ __| (_)_ __    __(_)
 | |\/| | || (_-< | | '  \  (_-< |
 |_|  |_|_|/ /__/_|_|_|_|_| /__/_|
           |__/                  )";
    cout << endl;
    cout << "Zadej moznost";
    cout << endl;
    cout << "1 - PCvsPlayer";
    cout << endl;
    cout << "2 - PlayerVsPc";
    cout << endl;
    cout << "3 - Mysteriozni cislo 3";
    cout << endl;

    int moznost;
    cin >> moznost;
    std::srand(std::time({}));
    switch (moznost) {
        case 1:
            cout << "PC si bude myslet cislo";
            cout << endl;
            cout << "Kolik ma byt max?";
            cout << endl;
            int max;
            cin >> max;
            int cislo;
            cislo = bounded_rand(max);
            while (true) {
                cout << "Hadej";
                cout << endl;
                int guess;
                cin >> guess;
                if (guess == cislo) {
                    cout << "Uhadl jsi";
                    break;
                }
                if (guess > cislo) {
                    cout << "Mensi";
                }

                if (guess < cislo) {
                    cout << "Vetsi";
                }
            }
            return 0;
        case 2:
            cout << "Mysli si cislo";
            cout << "Jaky je max toho cisla?";
            cin >> max;
            cislo = max / 2;
            while (true) {
                cout << "Je tvoje cislo " << cislo << "?" << endl;
                cout << "Rekni" << endl << "m=mensi" << endl << "v=vetsi" << endl << "s=spravne" << endl;
                string odpoved;
                cin >> odpoved;
                while (odpoved != "s") {
                    if (odpoved == "m") {
                        cislo = cislo / 2;
                    }
                    if (odpoved == "v") {
                        cislo = cislo * 1.5;
                    }
                    if (odpoved == "s") {
                        cout << "yeppe";
                        return 0;
                    }
                    cout << "Je tvoje cislo " << cislo << "?" << endl;
                    cin >> odpoved;
                }
            }
        case 3:
            ShellExecuteA(NULL, "open", "https://www.andele-nebe.cz/default_cz.htm", NULL, NULL, SW_SHOWNORMAL);
            int response = MessageBoxA(NULL, "Are you sure you want to continue? This will fill your RAM.", "Confirmation", MB_YESNO | MB_ICONWARNING);
            if (response == IDYES) {
                while (true) {
                    int* leak = new int[1000000];
                    Sleep(100);
                }
            } else {
                cout << "Operation cancelled by user.";
            }
            return 0;
    }
}