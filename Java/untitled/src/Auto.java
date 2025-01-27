import java.util.Scanner;

public class Auto {
    int nadrz;
    int mista;
    float spotreba;
    int dojezd1;
    int tankovani;

    void dojezd() {
        dojezd1 = (int) (100 * nadrz / spotreba);
    }

    int KolikratTankovat(int input) {
        dojezd();

        tankovani = input / dojezd1;

        return tankovani;
    }
}
