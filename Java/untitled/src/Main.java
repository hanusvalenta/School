import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Auto ford = new Auto();
        Auto skoda = new Auto();

        ford.nadrz = 50;
        ford.mista = 5;
        ford.spotreba = 6.5f;

        skoda.nadrz = 25;
        skoda.mista = 4;
        skoda.spotreba = 5f;

        System.out.println("Zadej kolik hm chceš ujet");
        System.out.println("musíš " + skoda.KolikratTankovat(scanner.nextInt()) + "krát natankovat");
    }
}