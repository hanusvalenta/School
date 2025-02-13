public class Output {
    public void printScore(Hrac player1, Hrac player2, String[] pointNames) {
        String player1Score = player1.hasAdvantage() ? "Ad" : pointNames[player1.getPoints()];
        String player2Score = player2.hasAdvantage() ? "Ad" : pointNames[player2.getPoints()];

        System.out.println(player1.getName() + " has " + player1Score + " points and " +
                player1.getSets() + " sets, " + player1.getGames() + " games");
        System.out.println(player2.getName() + " has " + player2Score + " points and " +
                player2.getSets() + " sets, " + player2.getGames() + " games");
    }

    public void announceSetWin(String playerName) {
        System.out.println(playerName + " wins!");
    }

    public void announceMatchWin(String playerName) {
        System.out.println(playerName + " wins the match!");
    }
}