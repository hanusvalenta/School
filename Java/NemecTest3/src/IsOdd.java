import java.util.Scanner;
public class IsOdd {
    Scanner scanner = new Scanner(System.in);
    boolean isOdd() {
        return scanner.nextInt() % 2 == 1;
    }
}