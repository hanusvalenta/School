import java.util.Random;
import java.util.Scanner;

public class Ucet {
    public static void main(String[] args)
    {
        Random rand = new Random();

        if(args[0].equals("false")){
            int cislo = 0;
            if(cislo == 0){
                cislo = rand.nextInt(9999999);
            }

            String Jmeno = "Mirek Umtrlich";
            String Adresa = "Hnevotinska 67";
            Boolean Karta = false;
            int Penizky = 0;
        }
        int cislo = 0;
        if(cislo == 0){
            cislo = rand.nextInt(9999999);
        }

        String Jmeno = "Mirek Umtrlich";
        String Adresa = "Hnevotinska 67";
        Boolean Karta = false;
        int Penizky = 0;

        System.out.println("\nProsím zadej akci: \n1. Vybrat peníze\n2. Vložit peníze\n3. Informace o účtu");

        Scanner input = new Scanner(System.in);
        int Akce = input.nextInt();

        while (Akce < 1 || Akce > 3) {
            System.out.println("Neplatná akce prosím zadej novou: \n1. Vybrat peníze\n2. Vložit peníze\n3. Informace o účtu");
            Akce = input.nextInt();
        }
        switch (Akce){
            case 1:
                Vyber();
            case 2:
                Vklad();
            case 3:
                Info(cislo, Jmeno, Adresa, Karta, Penizky);
        }
    }

    public static void Vyber(){

    }
    public static void Vklad(){

    }
    public static void Info(int cislo, String Jmeno, String Adresa, boolean Karta, int Penizky){
        if(Karta == false){
            System.out.println("Vypadá to že nemáte kartu chete si ji založit? Ano/Ne");
            Scanner input = new Scanner(System.in);
            Boolean PlatnaOd = false;

            while(true){
                String Akce = input.nextLine();
                Akce = Akce.toLowerCase();
                if (Akce.equals("ano")){
                    Karta = true;
                    System.out.println("Karta zalozena");
                    PlatnaOd = true;
                    break;
                }
                if (Akce.equals("ne")){
                    break;
                }
                else if (PlatnaOd == false){
                    System.out.println("Neplatna odpoved Ano/Ne");
                }
            }
        }

        System.out.println("Cislo - " + cislo);
        System.out.println("Jmeno - " + Jmeno);
        System.out.println("Adresa - " + Adresa);
        if (Karta){
            System.out.println("Karta - Ano");
        }
        else{
            System.out.println("Karta - Nah");
        }
        System.out.println("Penizky - " + Penizky + "Kč");
    }
}
