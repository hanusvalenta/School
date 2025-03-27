import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ask = new Scanner(System.in);

        System.out.println("Zadej pujcenou castku");
        double castka = ask.nextDouble() * 1000000;

        System.out.println("Zadej na kolik let");
        double roky = ask.nextDouble() * 12;

        System.out.println("Zadej na procento uroku");
        double urokProcento = ask.nextDouble();

        double urok = castka * urokProcento / 100;

        double splatka = castka / roky + urok;

        double celkem = castka;

        double umor = 0;

        for (int i = 1; i < roky; i++) {
            if (castka >= 0) {
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("Mesic: " + i + " Splatka: " + (int)splatka + " Urok: " + (int)urok + " Umor " + (int)umor + " Zustatek " + (int)castka);

                castka -= umor;

                umor = splatka - urok;

                urok = castka * urokProcento / 100;

                celkem += urok;
            } else {
                System.out.println("=========================================================================================");
                System.out.println("Mesic: " + i + " Splaceno // Dohramady jste zplatily " + (int)celkem);
                break;
            }
        }
    }
}