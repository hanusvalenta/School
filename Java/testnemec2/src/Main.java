import java.text.MessageFormat;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Main {
    static String Jmeno = "";
    static boolean Pohlavi = false;
    static String PhS = "";
    static int Znamka = 0;
    static String Zlepsit = "";
    static String Predmet = "";
    static int TextIndex = 0;
    static String[] Text1 = {"", "", ""};
    static String[] Text2 = {"", "", ""};
    static String[] Text3 = {"", "", ""};
    static String[] Text4 = {"", "", ""};
    static String[] Text5 = {"", "", ""};
    static String Osloveni = "";
    static String OsloveniKoncovka = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        TextIndex = random.nextInt(3);

        System.out.println("Zadej křestní jméno v 1. pádě:");
        Jmeno = scanner.nextLine();

        System.out.println("Zadej pohlaví K/H:");
        PhS = scanner.nextLine();
        if (Objects.equals(PhS, "K") || Objects.equals(PhS, "k")) {
            Pohlavi = true;
        }
        else if (Objects.equals(PhS, "H") || Objects.equals(PhS, "h")) {
            Pohlavi = false;
        }

        System.out.println("Zadej známku:");
        Znamka = Integer.parseInt(scanner.nextLine());

        System.out.println("Zadej název předmětu v 3. pádě:");
        Predmet = scanner.nextLine();

        System.out.println("Zadej v čem by se měl zlepšit v 3. pádě:");
        Zlepsit = scanner.nextLine();

        if (Pohlavi == false) {
            Osloveni = "zvládla";
            OsloveniKoncovka = "a";
        }
        else if (Pohlavi == true) {
            Osloveni = "zvládl";
            OsloveniKoncovka = "";
        }

        Text1[0] = MessageFormat.format("{0} {1} {2} na výbornou. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, Osloveni, Predmet, Znamka, Zlepsit);
        Text1[1] = MessageFormat.format("{0} ukázal{1} výborné znalosti v {2}. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, OsloveniKoncovka, Predmet, Znamka, Zlepsit);
        Text1[2] = MessageFormat.format("{0} se výborně orientuje v {1}. Známka: {2}. Doporučuji se ale zlepšit v {3}.", Jmeno, Predmet, Znamka, Zlepsit);

        Text2[0] = MessageFormat.format("{0} ukázal{1} spolehlivé znalosti v {2}. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, OsloveniKoncovka, Predmet, Znamka, Zlepsit);
        Text2[1] = MessageFormat.format("{0} dosahuje dobrých výsledků v {1}. Známka: {2}. Doporučuji se ale zlepšit v {3}.", Jmeno, Predmet, Znamka, Zlepsit);
        Text2[2] = MessageFormat.format("{0} v {1} ukázal{2} solidní úroveň. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, OsloveniKoncovka, Predmet, Znamka, Zlepsit);

        Text3[0] = MessageFormat.format("{0} má v {1} základní znalosti. Známka: {2}. Doporučuji se ale zlepšit v {3}.", Jmeno, Predmet, Znamka, Zlepsit);
        Text3[1] = MessageFormat.format("{0} v {1} dosáhl{2} průměrných výsledků. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, Predmet, OsloveniKoncovka, Znamka, Zlepsit);
        Text3[2] = MessageFormat.format("{0} prokázal{1} určité rezervy v {2}. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, OsloveniKoncovka, Predmet, Znamka, Zlepsit);

        Text4[0] = MessageFormat.format("{0} má v {1} značné rezervy. Známka: {2}. Doporučuji se ale zlepšit v {3}.", Jmeno, Predmet, Znamka, Zlepsit);
        Text4[1] = MessageFormat.format("{0} v {1} nedosáhl{2} požadované úrovně. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, OsloveniKoncovka, Predmet, Znamka, Zlepsit);
        Text4[2] = MessageFormat.format("{0} by měl intenzivně pracovat na zlepšení v {1}. Známka: {2}. Doporučuji se ale zlepšit v {3}.", Jmeno, Predmet, Znamka, Zlepsit);

        Text5[0] = MessageFormat.format("{0} v {1} neprokázal{2} dostatečné znalosti. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, OsloveniKoncovka, Predmet, Znamka, Zlepsit);
        Text5[1] = MessageFormat.format("{0} v {1} nesplnil{2} očekávání. Známka: {3}. Doporučuji se ale zlepšit v {4}.", Jmeno, OsloveniKoncovka, Predmet, Znamka, Zlepsit);
        Text5[2] = MessageFormat.format("{0} by měl zaměřit více pozornosti na {1}. Známka: {2}. Doporučuji se ale zlepšit v {3}.", Jmeno, Predmet, Znamka, Zlepsit);

        if (Znamka == 1) {
            System.out.println(Text1[TextIndex]);
        }
        else if (Znamka == 2) {
            System.out.println(Text2[TextIndex]);
        }
        else if (Znamka == 3) {
            System.out.println(Text3[TextIndex]);
        }
        else if (Znamka == 4) {
            System.out.println(Text4[TextIndex]);
        }
        else if (Znamka == 5) {
            System.out.println(Text5[TextIndex]);
        }
        scanner.close();
    }
}
