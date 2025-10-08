import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println((char)27 + "[39;43m" + "Vítej v LBank konsolovém interface \n");

        String maUcet = "false";


        while(true){
            System.out.println("\nProsím zadej akci: \n1. Založit nebo zobrazit účet\n2. Založit nebo kontokorent účet\n3. Dostat úver\n4. Odejit");

            Scanner input = new Scanner(System.in);
            int Akce = input.nextInt();

            while (Akce < 1 || Akce > 4) {
                System.out.println("Neplatná akce prosím zadej novou: \n1. Založit nebo zobrazit účet\n2. Založit nebo kontokorent účet\n3. Dostat úver");
                Akce = input.nextInt();
            }
            switch (Akce){
                case 1:
                    if(maUcet.equals("false")){
                        System.out.println("Ucet nenalezen -- Zakladam novi Ucet");
                        Ucet ucet = new Ucet();
                        ucet.main(new String[]{maUcet});
                        maUcet = "True";
                    }
                    if(maUcet.equals("true")){
                        System.out.println("Ucet nalezen");
                        Ucet ucet = new Ucet();
                        ucet.main(new String[]{maUcet});
                    }
                case 2:

                case 3:

                case 4:
                    break;
            }
        }
    }
}