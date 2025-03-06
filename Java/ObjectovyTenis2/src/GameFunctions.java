public class GameFunctions {
    static Player hrac = new Player();

    public static void printScore() {
        String player1Score = Player.Player1Advantage ? "Ad" : Player.pointNames[Player.Player1Points];
        String player2Score = Player.Player2Advantage ? "Ad" : Player.pointNames[Player.Player2Points];

        System.out.println("Player 1 has " + player1Score + " points and " + Player.Player1Sets + " sets, " + Player.Player1Games + " games");
        System.out.println("Player 2 has " + player2Score + " points and " + Player.Player2Sets + " sets, " + Player.Player2Games + " games");
    }

    public static void resetGame() {
        Player.Player1Points = 0;
        Player.Player2Points = 0;
        Player.Player1Advantage = false;
        Player.Player2Advantage = false;
    }
}
