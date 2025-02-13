public class Game {
    private Hrac player1;
    private Hrac player2;
    private Input input;
    private Output output;
    private boolean gameOver;
    private final String[] pointNames = {"0", "15", "30", "40"};

    public Game() {
        this.player1 = new Hrac("Player 1");
        this.player2 = new Hrac("Player 2");
        this.input = new Input();
        this.output = new Output();
        this.gameOver = false;
    }

    public void gameStart() {
        while (!gameOver) {
            output.printScore(player1, player2, pointNames);
            String winner = input.getWinner();

            if (winner.equals("1")) {
                handlePoint(player1, player2);
            } else if (winner.equals("2")) {
                handlePoint(player2, player1);
            }

            checkSetWin();
            checkMatchWin();
        }
        input.close();
    }

    private void handlePoint(Hrac scoringPlayer, Hrac otherPlayer) {
        if (scoringPlayer.hasAdvantage()) {
            scoringPlayer.winGame();
            resetGame();
        } else if (scoringPlayer.getPoints() == 3 && otherPlayer.getPoints() == 3) {
            scoringPlayer.setAdvantage(true);
            otherPlayer.setAdvantage(false);
        } else if (scoringPlayer.getPoints() == 3) {
            if (otherPlayer.hasAdvantage()) {
                otherPlayer.setAdvantage(false);
            } else {
                scoringPlayer.winGame();
                resetGame();
            }
        } else {
            scoringPlayer.addPoint();
        }
    }

    private void resetGame() {
        player1.resetPoints();
        player2.resetPoints();
    }

    private void checkSetWin() {
        if (player1.getGames() >= 6 && player1.getGames() - player2.getGames() >= 2) {
            player1.winSet();
            resetGames();
            output.announceSetWin(player1.getName());
        } else if (player2.getGames() >= 6 && player2.getGames() - player1.getGames() >= 2) {
            player2.winSet();
            resetGames();
            output.announceSetWin(player2.getName());
        }
    }

    private void resetGames() {
        player1.resetGames();
        player2.resetGames();
    }

    private void checkMatchWin() {
        if (player1.getSets() == 2) {
            output.announceMatchWin(player1.getName());
            gameOver = true;
        } else if (player2.getSets() == 2) {
            output.announceMatchWin(player2.getName());
            gameOver = true;
        }
    }
}