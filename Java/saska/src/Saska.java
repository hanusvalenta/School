import java.util.Scanner;
import java.util.Random;

public class Saska {
    private static boolean obsahuje(int[] array, int number, int limit) {
        for (int i = 0; i < limit; i++) {
            if (array[i] == number) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int[] userNumbers = new int[6];
        int[] secondUserNumbers = new int[2];
        int[] randomSet1 = new int[6];
        int[] randomSet2 = new int[2];

        System.out.println("Zadej 6 čísel mezi 1 a 32:");
        for (int i = 0; i < 6; i++) {
            while (true) {
                int number = scanner.nextInt();
                if (number >= 1 && number <= 32 && !obsahuje(userNumbers, number, i)) {
                    userNumbers[i] = number;
                    break;
                } else {
                    System.out.println("Zadej číslo mezi 1 a 32, které ještě nebylo zadáno:");
                }
            }
        }

        System.out.println("Zadej dvě dodatková mezi 1 a 10:");
        for (int i = 0; i < 2; i++) {
            while (true) {
                int number = scanner.nextInt();
                if (number >= 1 && number <= 10 && !obsahuje(secondUserNumbers, number, i)) {
                    secondUserNumbers[i] = number;
                    break;
                } else {
                    System.out.println("Zadej číslo mezi 1 a 10, které ještě nebylo zadáno:");
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            int number;
            do {
                number = random.nextInt(32) + 1;
            } while (obsahuje(randomSet1, number, i));
            randomSet1[i] = number;
        }

        for (int i = 0; i < 2; i++) {
            int number;
            do {
                number = random.nextInt(10) + 1;
            } while (obsahuje(randomSet2, number, i));
            randomSet2[i] = number;
        }

        System.out.println("\nVygenerovaná čísla:");
        for (int num : randomSet1) System.out.print(num + " ");
        System.out.println();
        for (int num : randomSet2) System.out.print(num + " ");
        System.out.println();

        int matches1 = 0, matches2 = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (userNumbers[i] == randomSet1[j]) {
                    matches1++;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (secondUserNumbers[i] == randomSet2[j]) {
                    matches2++;
                }
            }
        }

        System.out.println("\nPočet shod v hlavním poli: " + matches1);
        System.out.println("Počet shod v dodatkovém poli: " + matches2);

        if (matches1 == 6 & matches2 == 2) {
            System.out.println("Gratuluju vyhrál si milion");
        }
    }
}