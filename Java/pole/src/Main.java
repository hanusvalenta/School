public class Main {
    public static void main(String[] args) {
        int pole[] = {4,14,5,1,9,40};
        int nejVetsi = pole[0];
        int nejMensi = pole[0];

        for (int i = 0; i < pole.length; i++) {
            if (pole[i] > nejVetsi) {
                nejVetsi = pole[i];
            }
            if (pole[i] < nejMensi) {
                nejMensi = pole[i];
            }
        }

        System.out.println(nejVetsi + " " + nejMensi);
    }
}