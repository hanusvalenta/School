/*
1. Vytvořte jednoduchý příklad, ve kterém bude program za pomocí vyjímek zachytávat překročení hranice pole. Do programu zakomponujte i blok finally.
2. Vytvořte vlastní výjimku, která se vyvolá v případě, že budeme dělit dvě čísla a jejích podíl nebude celé číslo. Při vyvolání této vyjímky se v konzoli vypíše text: „Výsledkem operace i/y není celé číslo!“ (i a y zde reprezentují dělenec a dělitel).
3. Vytvořte metodu, která dělí dvě čísla. Do programu tuto metodu zakomponujte a ošetřete ji tak, aby nedošlo k pádu programu v případě dělní nulou. Metodu označte tak, aby bylo jasné, že může vyvolat vyjímku.
4. Vytvořte příklad, který bude moci vyvolávat dva druhy vyjímek (překročení hranice pole a dělení nulou). Oba druhy vyjímek ošetřete tak, že v případě dělení nulou bude program pokračovat po vypsání chybové zprávy „Nelze dělit nulou“ a v případě překročení hranice pole bude program okamžitě ukončen se zprávou „Fatální chyba“.
*/
class NonIntegerDivisionException extends Exception {
    public NonIntegerDivisionException(int i, int y) {
        super("Výsledkem operace " + i + " / " + y + " není celé číslo!");
    }
}

public class Main {
    public static int vydelSVyjimkami(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Nulou delit nejde");
        }
        return a / b;
    }

    public static int vydelint(int i, int y) throws NonIntegerDivisionException, ArithmeticException {
        if (y == 0) {
            throw new ArithmeticException("Nulou nejde dělit");
        }
        if (i % y != 0) {
            throw new NonIntegerDivisionException(i, y);
        }
        return i / y;
    }

    public static void main(String[] args) {
        int[] cisla = {1, 2, 3};
        try {
            int cislo = cisla[3];
            System.out.println(cislo);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Pole přeteklo");
        } finally {
            System.out.println("finally");
        }

        try {
            int vysledek1 = vydelint(10, 3);
            System.out.println(vysledek1);
        } catch (NonIntegerDivisionException e) {
            System.out.println("Výjimka " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        try {
            int vysledek2 = vydelint(10, 5);
            System.out.println(vysledek2);
        } catch (NonIntegerDivisionException | ArithmeticException e) {
            System.out.println("Výjimka " + e.getMessage());
        }

        try {
            vydelSVyjimkami(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Divide výjimka " + e.getMessage());
        }

        int[] jineCisla = {10, 0, 5, 2};
        int[] indexy = {0, 2, 5, 0};

        for (int i = 0; i < jineCisla.length; i++) {
            try {
                int vysledek = jineCisla[indexy[i]] / jineCisla[i];
                System.out.println(vysledek);
            } catch (ArithmeticException e) {
                System.out.println("Nelze dělit nulou");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Fatální chyba");
                System.exit(0);
            }
        }
    }
}