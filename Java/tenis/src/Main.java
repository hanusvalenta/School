import java.util.Scanner;

public class Main {
    static boolean GameOver = false;
    static int Player1Games = 0;
    static int Player2Games = 0;
    static int Player1Sets = 0;
    static int Player2Sets = 0;
    static int Player1Points = 0;
    static int Player2Points = 0;
    static boolean Player1Advantage = false;
    static boolean Player2Advantage = false;
    static final String[] pointNames = {"0", "15", "30", "40"};

    public static void printScore() {
        String player1Score = Player1Advantage ? "Ad" : pointNames[Player1Points];
        String player2Score = Player2Advantage ? "Ad" : pointNames[Player2Points];

        System.out.println("Player 1 has " + player1Score + " points and " + Player1Sets + " sets, " + Player1Games + " games");
        System.out.println("Player 2 has " + player2Score + " points and " + Player2Sets + " sets, " + Player2Games + " games");
    }

    public static void resetGame() {
        Player1Points = 0;
        Player2Points = 0;
        Player1Advantage = false;
        Player2Advantage = false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (!GameOver) {
            printScore();
            System.out.println("Who won? 1/2");
            String whoWon = scanner.nextLine();

            if (whoWon.equals("1")) {
                if (Player1Advantage) {
                    Player1Games++;
                    resetGame();
                } else if (Player1Points == 3 && Player2Points == 3) {
                    Player1Advantage = true;
                    Player2Advantage = false;
                } else if (Player1Points == 3) {
                    if (Player2Advantage) {
                        Player2Advantage = false;
                    } else {
                        Player1Games++;
                        resetGame();
                    }
                } else {
                    Player1Points++;
                }
            } else if (whoWon.equals("2")) {
                if (Player2Advantage) {
                    Player2Games++;
                    resetGame();
                } else if (Player2Points == 3 && Player1Points == 3) {
                    Player2Advantage = true;
                    Player1Advantage = false;
                } else if (Player2Points == 3) {
                    if (Player1Advantage) {
                        Player1Advantage = false;
                    } else {
                        Player2Games++;
                        resetGame();
                    }
                } else {
                    Player2Points++;
                }
            }

            if (Player1Games >= 6 && Player1Games - Player2Games >= 2) {
                Player1Sets++;
                Player1Games = 0;
                Player2Games = 0;
                System.out.println("Player 1 wins the set!");
            } else if (Player2Games >= 6 && Player2Games - Player1Games >= 2) {
                Player2Sets++;
                Player1Games = 0;
                Player2Games = 0;
                System.out.println("Player 2 wins the set!");
            }

            if (Player1Sets == 2) {
                System.out.println("Player 1 wins the match!");
                GameOver = true;
            } else if (Player2Sets == 2) {
                System.out.println("Player 2 wins the match!");
                GameOver = true;
            }
        }
        scanner.close();
    }
}
