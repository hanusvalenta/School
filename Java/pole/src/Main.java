public class Main {
    public static void main(String[] args) {
        int pole[] = {4,14,5,7,9,20};
        int soucet = 0;

        for (int i = 0; i < pole.length; i++) {
            soucet += pole[i];
        }

        int prumer = soucet/pole.length;

        System.out.println(prumer);
    }
}