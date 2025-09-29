import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Vítej v LBank konsolovém interface \n");

        System.out.println("\nProsím zadej akci: \n1. Založit nebo zobrazit účet\n2. Založit nebo kontokorent účet\n3. Dostat úver");

        Scanner input = new Scanner(System.in);
        int Akce = input.nextInt();

        while (Akce < 1 || Akce > 3) {
            System.out.println("Neplatná akce prosím zadej novou: \n1. Založit nebo zobrazit účet\n2. Založit nebo kontokorent účet\n3. Dostat úver");
            Akce = input.nextInt();
        }
        switch (Akce){
            case 1:
                Ucet ucet = new Ucet();
            case 2:

            case 3:
        }
    }
}