public class Player {
    static boolean GameOver = false;
    static int Player1Games = 0;
    static int Player2Games = 0;
    static int Player1Sets = 0;
    static int Player2Sets = 0;
    static int Player1Points = 0;
    static int Player2Points = 0;
    static boolean Player1Advantage = false;
    static boolean Player2Advantage = false;
    static String[] pointNames = {"0", "15", "30", "40"};

    public static void whoWonChanges(String whoWon) {
        if (whoWon.equals("1")) {
            if (Player1Advantage) {
                Player1Games++;
                GameFunctions.resetGame();
            } else if (Player1Points == 3 && Player2Points == 3) {
                Player1Advantage = true;
                Player2Advantage = false;
            } else if (Player1Points == 3) {
                if (Player2Advantage) {
                    Player2Advantage = false;
                } else {
                    Player1Games++;
                    GameFunctions.resetGame();
                }
            } else {
                Player.Player1Points++;
            }
        }
        else if (whoWon.equals("2")) {
            if (Player2Advantage) {
                Player2Games++;
                GameFunctions.resetGame();
            } else if (Player2Points == 3 && Player1Points == 3) {
                Player2Advantage = true;
                Player1Advantage = false;
            } else if (Player2Points == 3) {
                if (Player1Advantage) {
                    Player1Advantage = false;
                } else {
                    Player2Games++;
                    GameFunctions.resetGame();
                }
            } else {
                Player2Points++;
            }
        }
    }

    public static void checkWins() {
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
    }

    public static void checkWinsMatch() {
        if (Player1Sets == 2) {
            System.out.println("Player 1 wins the match!");
            GameOver = true;
        } else if (Player.Player2Sets == 2) {
            System.out.println("Player 2 wins the match!");
            GameOver = true;
        }
    }
}