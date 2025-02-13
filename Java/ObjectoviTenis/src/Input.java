import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getWinner() {
        System.out.println("Who won? 1/2");
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}