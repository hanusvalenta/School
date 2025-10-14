import java.util.Scanner;

public class Uver {

    private final int nabizenaVyseUveru = 10000;
    
    public void PoptatUver(Ucet ucet){
        if (ucet.getDluhUveru() > 0) {
            System.out.println("Nezplacený úvěr: " + ucet.getDluhUveru() + " kč");
            return;
        }
        
        System.out.println("Max úver je " + nabizenaVyseUveru + " kč Vybrat? Ano Ne");
        Scanner input = new Scanner(System.in);
        String odpoved = input.nextLine().toLowerCase();
        
        if (odpoved.equals("ano")){
            ucet.Vklad(nabizenaVyseUveru); 
            ucet.setDluhUveru(nabizenaVyseUveru);
            System.out.println("Úvěr " + nabizenaVyseUveru + " kč přidán | Nový dluh: " + nabizenaVyseUveru + " kč");
        }
    }
    
    public void SplatitUver(Ucet ucet){
        if (ucet.getDluhUveru() <= 0) {
            System.out.println("Není úver k zplacení");
            return;
        }
        
        System.out.println("Dluh: " + ucet.getDluhUveru() + " kč | Kolik zplatit?");
        Scanner input = new Scanner(System.in);
        
        if (input.hasNextInt()){
            int castka = input.nextInt();
            input.nextLine();
            
            if (castka <= 0) {
                System.out.println("Zplatit jdou jenom normální částky");
                return;
            }
            
            if (castka > ucet.getDluhUveru()) {
                castka = ucet.getDluhUveru(); 
            }
            
            if (ucet instanceof Kontokorent) {
                if (ucet.getZustatek() - castka < ((Kontokorent) ucet).getLimitKontokorentu()) {
                    System.out.println("Nedostatek peněz k zplacení:" + castka + " kč");
                    return;
                }
            } else if (ucet.getZustatek() < castka) {
                System.out.println("Nedostatek peněz k zplacení: " + castka + " kč");
                return;
            }
                 
            ucet.Vyber(castka); 
            ucet.setDluhUveru(ucet.getDluhUveru() - castka);
            
            System.out.println("Zplaceno " + castka + " kč");
            if (ucet.getDluhUveru() > 0) {
                System.out.println("Zbývá: " + ucet.getDluhUveru() + " kč");
            }
            
        } else {
            System.out.println("Neplatná částka");
            input.nextLine();
        }
    }

    public void InfoUver(Ucet ucet){
        System.out.println("\n--- Info ---");
        System.out.println("Nezplaceno: " + ucet.getDluhUveru() + " kč");
        System.out.println("--------------------------\n");
    }
}