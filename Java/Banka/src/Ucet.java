import java.util.Random;
import java.util.Scanner;

public class Ucet {
    private int cisloBUctu;
    private String jmenoKlienta;
    private String adresaKlienta;
    private boolean maKarta;
    protected int zustatek;

    private int dluhUveru = 0;

    public Ucet(int cisloUctu) {
        Random rand = new Random();
        if (cisloUctu == 0) {
            this.cisloBUctu = rand.nextInt(9000000) + 1000000;
        }
        else {
            this.cisloBUctu = cisloUctu;
        }
        this.jmenoKlienta = "Mirek Umtrlich";
        this.adresaKlienta = "Hnevotinska 67";
        this.maKarta = false;
        this.zustatek = 0;
        System.out.println("Bankovní účet založen | číslo: " + this.cisloBUctu);
    }
    
    public int getcisloBUctu() {
        return cisloBUctu;
    }

    public int getZustatek() {
        return zustatek;
    }
    
    public int getDluhUveru() {
        return dluhUveru;
    }
    
    public void setDluhUveru(int dluh) {
        this.dluhUveru = dluh;
    }
    
    public boolean getMaKarta() {
        return maKarta;
    }

    public void Vyber(int castka){
        if (castka < 0) {
            System.out.println("Dobry pokus zmrde");
        }
        else if (this.zustatek >= castka) {
            this.zustatek -= castka;
            System.out.println("Výběr " + castka + " kč proběhl ok | nový zůstatek: " + this.zustatek + " kč");
        } else {
            System.out.println("Nemůžeš vybrat tolik máš jenom" + this.zustatek + " kč.");
        }
    }
    
    public void Vklad(int castka){
        if (castka > 0) {
            this.zustatek += castka;
            System.out.println("Vklad " + castka + " kč proběhl ok | nový zůstatek: " + this.zustatek + " kč");
        } else {
            System.out.println("Mínusové částky se nedají vložit");
        }
    }
    
    public void Info(Scanner input){
        if(!this.maKarta){
            System.out.println("Vypadá to že nemáte kartu. Chcete si ji založit? Ano/Ne");
            
            while(true){
                String Akce = input.nextLine().toLowerCase();
                if (Akce.equals("ano")){
                    this.maKarta = true;
                    System.out.println("Karta založena. Je platná.");
                    break;
                }
                if (Akce.equals("ne")){
                    System.out.println("Kartu nezakládáme.");
                    break;
                }
                else {
                    System.out.println("Neplatná odpověd");
                }
            }
        }
        
        System.out.println("\n--- Info učet ---");
        System.out.println("Číslo: " + this.cisloBUctu);
        System.out.println("Jméno: " + this.jmenoKlienta);
        System.out.println("Adresa: " + this.adresaKlienta);
        System.out.println("Karta: " + (this.maKarta));
        if (this.zustatek <= 0) {
            System.out.println("Zůstatek: " + "0" + " kč");
        }
        else {
            System.out.println("Zůstatek: " + this.zustatek + " kč");
        }
        if(this.dluhUveru > 0) {
            System.out.println("Ještě nemáš zplacený úvěr - " + this.dluhUveru + " kč");
        }
        System.out.println("------------------------\n");
    }

    public void Interakce(Scanner input){
        System.out.println("\nZadej akci: \n1. Vybrat peníze\n2. Vložit peníze\n3. Informace o účtu");

        int Akce = 0;
        
        if (input.hasNextInt()){
            Akce = input.nextInt();
            input.nextLine();
        } else {
            System.out.println("Špatný input");
            input.nextLine();
            return;
        }

        switch (Akce){
            case 1:
                System.out.println("Zadej kolik vybrat:");
                if (input.hasNextInt()){
                    Vyber(input.nextInt());
                    input.nextLine();
                } else {
                    System.out.println("Špatný input");
                    input.nextLine();
                }
                break;
            case 2:
                System.out.println("Zadej kolik vlozit:");
                if (input.hasNextInt()){
                    Vklad(input.nextInt());
                    input.nextLine();
                } else {
                    System.out.println("Špatný input");
                    input.nextLine();
                }
                break;
            case 3:
                Info(input);
                break;
            default:
                System.out.println("Špatná akce");
        }
    }
}