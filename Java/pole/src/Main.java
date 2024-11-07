public class Main {
    public static void main(String[] args) {
        int pole[] = {4,14,5,9,9,40};
        int soucet = 0;
        int sudaCisla = 0;

        for (int i = 0; i < pole.length; i++) {
            if (pole[i] % 2 == 0) {
                soucet += pole[i];
                sudaCisla++;
            } 
        }

        int prumer = soucet/sudaCisla;

        System.out.println(prumer);
    }
}