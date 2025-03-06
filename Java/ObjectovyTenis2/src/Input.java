import java.util.Scanner;

public class Input {
    static Scanner scanner = new Scanner(System.in);

    public static String input() {
        String Inputted;
        Inputted = scanner.nextLine();
        return Inputted;
    }

    public static void close() {
        scanner.close();
    }
}
