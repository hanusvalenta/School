public class Main {
    public static void main(String[] args) {
        int[][] pole = new int[4][4];

        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole.length; j++) {
                pole[i][j] = (int) Math.random();
            }
        }

        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole.length; j++) {
                if (j == 3) {
                    System.out.println(pole[i][j] + System.lineSeparator());
                }
                System.out.print(pole[i][j]);
            }
        }
    }
}