import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        int cisla[] = {4, 8, 16, 32, 64, 128, 256, 512};
        int delit[] = {2, 0, 4, 4, 0, 8};

        for (int i = 0; i < cisla.length; i++) {
            try {
                deleno(5, 0);
                throw new MojeVyjimka("lol");
            }
            catch (ArithmeticException e) {
                System.out.println("Delis nulou");
            }
            catch (MojeVyjimka e) {
                System.out.println(e);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Prekroceni pole");
            }
            catch (ArrayStoreException e) {
                System.out.println("Nejaka chyba");
            }
        }
    }

    public static int deleno(int a, int b) {

        if (b == 0){
            System.out.println("nelze");
            throw new ArithmeticException("nelze");
        }
        else {
            return a/b;

        }
    }
}