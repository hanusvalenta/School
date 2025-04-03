public class VnejsiTrida {
    static int[] cisla;

    VnejsiTrida() {
        cisla = new int[10];
        cisla[0] = 1;
        cisla[1] = 2;
        cisla[2] = 3;
    }

    void analyzuj(){
        System.out.println("Maximum je: " + VnitrniTrida.min());
        System.out.println("Minimum je: " + VnitrniTrida.max());
        System.out.println("Prumer je: " + VnitrniTrida.prumer());
    }

    class VnitrniTrida {
        static int min(){
            int a;
            a = cisla[0];
            for (int i = 0; i < cisla.length; i++) {
                if (cisla[i] < a) {
                    a = cisla[i];
                }
            }
            return a;
        }
        static int max(){
            int a;
            a = cisla[0];
            for (int i = 0; i < cisla.length; i++) {
                if (cisla[i] > a) {
                    a = cisla[i];
                }
            }
            return a;
        }

        static int prumer(){
            int a = 0;
            for (int i = 0; i < cisla.length; i++) {
                a += cisla[i];
            }
            return a / cisla.length;
        }
    }
}
