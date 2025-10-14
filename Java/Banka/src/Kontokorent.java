import java.util.Scanner;

public class Kontokorent extends Ucet {
    
    private int limitKontokorentu;

    public Kontokorent(int limit) {
        super();
        this.limitKontokorentu = limit;
    }
    
    public int getLimitKontokorentu() {
        return limitKontokorentu;
    }
    
    @Override
    public void Vyber(int castka){
        int novyZustatek = zustatek - castka;
        
        if (novyZustatek >= this.limitKontokorentu) {
            this.zustatek = novyZustatek;
            System.out.println("Výběr " + castka + " kč | Nový zůstatek: " + this.zustatek + " kč");
            if (this.zustatek < 0) {
                System.out.println("Jsi v kontokorentu | Do max zbývá " + (this.zustatek - this.limitKontokorentu) + " kč.");
            }
        } else {
            System.out.println("Více jak max | Nejde vybrat " + castka + " kč | Do max zbývá " + this.limitKontokorentu + " kč.");
        }
    }

    @Override
    public void Vklad(int castka){
        if (castka <= 0) {
            System.out.println("Jenom pozitivní částky");
            return;
        }
        
        if (zustatek < 0) {
            int dluhKeSplaceni = Math.abs(zustatek);
            int splaceno = Math.min(castka, dluhKeSplaceni); 
            
            zustatek += castka;
            System.out.println("Vklad " + castka + " kč ok " + splaceno + " kč splaceno");
            
        } else {
            super.Vklad(castka);
        }
        System.out.println("Zůstatek: " + this.zustatek + " kč");
    }

    public void InfoKontokorent(){
        System.out.println("\n--- Kontokorent ---");
        System.out.println("Limit: " + this.limitKontokorentu + " kč");
        if (zustatek < 0) {
            System.out.println("Dluh: " + Math.abs(zustatek) + " kč");
            System.out.println("Zbývá: " + (zustatek - this.limitKontokorentu) + " kč");
        }
        System.out.println("--------------------------------\n");
    }
}