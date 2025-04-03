import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ask = new Scanner(System.in);

        recuriveSecti secti = new recuriveSecti();

        System.out.println("Zadej cislo");
        int cislo = ask.nextInt();

        System.out.println(secti.scitej(cislo));
    }
}
