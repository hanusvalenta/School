#include <string>
#include <vector>
#include <ctime>
#include <cctype>
#include <windows.h>

using namespace std;

vector<string> Vety;
string Veta;
string ZobrazenaVeta;
string UhodnutePismena;
int PocetPokusu;
bool Konec_hry;

void InicializovatHru() {
    Veta = Vety[rand() % Vety.size()];
    ZobrazenaVeta.assign(Veta.length(), '_');
    for (size_t i = 0; i < Veta.length(); ++i) {
        if (Veta[i] == ' ') {
            ZobrazenaVeta[i] = ' ';
        }
    }
    UhodnutePismena = "";
    PocetPokusu = 10;
    Konec_hry = false;
}

void NakresliSibenici(HDC hdc, int pokusy) {
    int chyby = 10 - pokusy;
    HPEN hPen = CreatePen(PS_SOLID, 3, RGB(0, 0, 0));
    SelectObject(hdc, hPen);

    MoveToEx(hdc, 50, 350, NULL);
    LineTo(hdc, 150, 350);
    MoveToEx(hdc, 100, 350, NULL);
    LineTo(hdc, 100, 100);
    LineTo(hdc, 250, 100);
    LineTo(hdc, 250, 150);

    if (chyby > 0) Ellipse(hdc, 230, 150, 270, 190);
    if (chyby > 1) { MoveToEx(hdc, 250, 190, NULL); LineTo(hdc, 250, 260); }
    if (chyby > 2) { MoveToEx(hdc, 250, 210, NULL); LineTo(hdc, 220, 240); }
    if (chyby > 3) { MoveToEx(hdc, 250, 210, NULL); LineTo(hdc, 280, 240); }
    if (chyby > 4) { MoveToEx(hdc, 250, 260, NULL); LineTo(hdc, 220, 290); }
    if (chyby > 5) { MoveToEx(hdc, 250, 260, NULL); LineTo(hdc, 280, 290); }

    if (chyby > 6) { MoveToEx(hdc, 220, 165, NULL); LineTo(hdc, 230, 170); MoveToEx(hdc, 230, 165, NULL); LineTo(hdc, 220, 170); }
    if (chyby > 7) { MoveToEx(hdc, 270, 165, NULL); LineTo(hdc, 280, 170); MoveToEx(hdc, 280, 165, NULL); LineTo(hdc, 270, 170); }
    if (chyby > 8) { Arc(hdc, 240, 175, 260, 185, 240, 180, 260, 180); }
    if (chyby > 9) { TextOut(hdc, 230, 195, "RIP", 3); }

    DeleteObject(hPen);
}

LRESULT CALLBACK WindowProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam) {
    switch (uMsg) {
        case WM_CREATE:
            srand(time(0));
            Vety = {
                "Epstein se nezabil", "Chemtrails umyslne","Alieni mezi nami", "jedenact zari kontrolovany vybuch", "Covid laboratorni unik","Media kontrolovana", "Moon landing falesne", "Izrael hladomor taktika", "Vacciny nebezpecne", "Dukazy zniceny","JFK vrazda planovana", "Rwanda genocida hutu", "Iraq valka falesna", "Area padesatjedna UFO", "Scientology tajne dokumenty","Waco ohniva strelba", "Ruby Ridge FBI", "Jonestown masakr", "Raeliani klonovani", "Moonie svatby"
            };
            InicializovatHru();
            break;

        case WM_DESTROY:
            PostQuitMessage(0);
            return 0;

        case WM_PAINT: {
            PAINTSTRUCT ps;
            HDC hdc = BeginPaint(hwnd, &ps);

            FillRect(hdc, &ps.rcPaint, (HBRUSH)(COLOR_WINDOW + 1));

            HFONT hFont = CreateFont(30, 0, 0, 0, FW_NORMAL, FALSE, FALSE, FALSE, DEFAULT_CHARSET, OUT_DEFAULT_PRECIS, CLIP_DEFAULT_PRECIS, DEFAULT_QUALITY, DEFAULT_PITCH | FF_SWISS, "Arial");
            SelectObject(hdc, hFont);

            TextOut(hdc, 50, 20, ZobrazenaVeta.c_str(), ZobrazenaVeta.length());

            string info = "Zbyvajici pokusy: " + to_string(PocetPokusu);
            TextOut(hdc, 50, 400, info.c_str(), info.length());

            string hadane = "Hadane pismena: " + UhodnutePismena;
            TextOut(hdc, 50, 450, hadane.c_str(), hadane.length());

            NakresliSibenici(hdc, PocetPokusu);

            if (Konec_hry) {
                SetTextColor(hdc, (Veta == ZobrazenaVeta) ? RGB(0, 128, 0) : RGB(255, 0, 0));
                const char* msg = (Veta == ZobrazenaVeta) ? "Gratuluju, uhodl jsi! :3" : "Prohral jsi :c";
                TextOut(hdc, 350, 150, msg, strlen(msg));
                string cele_slovo = "Spravna veta: " + Veta;
                TextOut(hdc, 350, 180, cele_slovo.c_str(), cele_slovo.length());
                TextOut(hdc, 350, 220, "Stiskni ENTER pro novou hru.", 29);
            } else {
                 TextOut(hdc, 350, 150, "Hadej pismeno!", 15);
            }

            DeleteObject(hFont);
            EndPaint(hwnd, &ps);
        }
        return 0;

        case WM_CHAR: {
            if (Konec_hry) {
                if (wParam == VK_RETURN) {
                    InicializovatHru();
                    InvalidateRect(hwnd, NULL, TRUE);
                }
                return 0;
            }

            char pismeno = tolower((char)wParam);

            if (!isalpha(pismeno)) {
                return 0;
            }

            if (UhodnutePismena.find(pismeno) != string::npos) {
                MessageBeep(MB_ICONEXCLAMATION);
                return 0;
            }

            UhodnutePismena += pismeno;
            UhodnutePismena += ' ';

            bool pismenoNalezeno = false;
            for (size_t i = 0; i < Veta.length(); ++i) {
                if (tolower(Veta[i]) == pismeno) {
                    ZobrazenaVeta[i] = Veta[i];
                    pismenoNalezeno = true;
                }
            }

            if (!pismenoNalezeno) {
                PocetPokusu--;
            }

            if (ZobrazenaVeta == Veta || PocetPokusu <= 0) {
                Konec_hry = true;
            }

            InvalidateRect(hwnd, NULL, TRUE);
        }
        return 0;
    }
    return DefWindowProc(hwnd, uMsg, wParam, lParam);
}

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow) {
    srand(time(0));

    const char CLASS_NAME[] = "SibeniceWindowClass";

    WNDCLASS wc = {};
    wc.lpfnWndProc = WindowProc;
    wc.hInstance = hInstance;
    wc.lpszClassName = CLASS_NAME;
    wc.hCursor = LoadCursor(NULL, IDC_ARROW);

    RegisterClass(&wc);

    HWND hwnd = CreateWindowEx(
        0,
        CLASS_NAME,
        "Sibenice GUI",
        WS_OVERLAPPEDWINDOW & ~WS_THICKFRAME & ~WS_MAXIMIZEBOX,
        CW_USEDEFAULT, CW_USEDEFAULT, 800, 600,
        NULL,
        NULL,
        hInstance,
        NULL
    );

    if (hwnd == NULL) {
        return 0;
    };

    ShowWindow(hwnd, nCmdShow);

    MSG msg = {};
    while (GetMessage(&msg, NULL, 0, 0) > 0) {
        TranslateMessage(&msg);
        DispatchMessage(&msg);
    }

    return 0;
}