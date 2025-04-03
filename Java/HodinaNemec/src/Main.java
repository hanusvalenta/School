import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ask = new Scanner(System.in);

        VnejsiTrida v = new VnejsiTrida();

        System.out.println("Zadej cislo");

        v.analyzuj();

        System.out.println();
    }
}
