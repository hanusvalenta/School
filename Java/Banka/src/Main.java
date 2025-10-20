import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println((char)27 + "[30;43m" + "Vítej v LBank konsolovém interface \n");

        Ucet aktualniUcet = null;
        Uver uverSluzba = new Uver(); 
        
        Scanner input = new Scanner(System.in);

        while(true){
            if (aktualniUcet == null) {
                System.out.println("1. Založit účet");
            } else if (aktualniUcet instanceof Kontokorent) {
                 System.out.println("1. Zobrazit | pracovat s kontokorent účtem");
                 System.out.println("2. Zrušit kontokorent | Info");
            } else {
                 System.out.println("1. Zobrazit | pracovat s účtem");
                 System.out.println("2. Založit kontokorent účet");
            }
            System.out.println("3. Dostat | zplatit úvěr");
            System.out.println("4. Exit");
            System.out.print("Zadej číslo akce: ");

            int Akce = 0;
            
            if (input.hasNextInt()){
                Akce = input.nextInt();
                input.nextLine(); 
            } else {
                System.out.println("Neplatný input");
                input.nextLine();
                continue;
            }

            if (Akce < 1 || Akce > 4) {
                System.out.println("Neplatný input");
                continue;
            }

            if (Akce > 1 && aktualniUcet == null) {
                System.out.println("Účet nenalezen :c");
                continue;
            }
            
            switch (Akce){
                case 1:
                    if(aktualniUcet == null){
                        aktualniUcet = new Ucet(0);
                    } else {
                        aktualniUcet.Interakce(input);
                    }
                    break;
                case 2:
                    if(aktualniUcet instanceof Kontokorent) {
                        System.out.println("Zrušit kontokorent? Ano | Ne | Info");
                        String volba = input.nextLine().toLowerCase();
                        
                        if (volba.equals("ano")) {
                            if (aktualniUcet.getZustatek() < 0) {
                                System.out.println("Nemůžete zrušit dokud jste v dluhu");
                            } else {
                                Ucet novyUcet = new Ucet(aktualniUcet.getcisloBUctu());
                                novyUcet.Vklad(aktualniUcet.getZustatek()); 
                                novyUcet.setDluhUveru(aktualniUcet.getDluhUveru()); 
                                
                                aktualniUcet = novyUcet;
                                System.out.println("Kontokorent zrušen nyní máte normální účet");
                            }
                        } else {
                           ((Kontokorent) aktualniUcet).InfoKontokorent();
                        }
                        
                    } else {
                        int limit = -10000;

                        Kontokorent novyKontokorent = new Kontokorent(limit, aktualniUcet.getcisloBUctu());

                        novyKontokorent.Vklad(aktualniUcet.getZustatek()); 
                        novyKontokorent.setDluhUveru(aktualniUcet.getDluhUveru()); 
                        
                        aktualniUcet = novyKontokorent;
                        System.out.println("Založen kontokorent s limitem " + limit + " kč");
                    }
                    break;
                case 3:
                    System.out.println("1. Vybrat úvěr\n2. Zplatit úvěr\n3. Info");
                    System.out.print("Vyber akci: ");
                    
                    if (input.hasNextInt()){
                        int uverAkce = input.nextInt();
                        input.nextLine();
                        switch (uverAkce){
                            case 1:
                                uverSluzba.PoptatUver(aktualniUcet);
                                break;
                            case 2:
                                uverSluzba.SplatitUver(aktualniUcet);
                                break;
                            case 3:
                                uverSluzba.InfoUver(aktualniUcet);
                                break;
                            default:
                                System.out.println("Špatný input");
                        }
                    } else {
                        System.out.println("Špatný input");
                        input.nextLine();
                    }
                    break;
                case 4:
                    return;
            }
        }
    }
}