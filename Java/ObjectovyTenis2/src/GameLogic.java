public class GameLogic {

    static Player hrac = new Player();
    static GameFunctions functions = new GameFunctions();
    static Input input = new Input();

    public void Start() {

        while (!Player.GameOver) {
            GameFunctions.printScore();
            System.out.println("Who won? 1/2");
            String whoWon = Input.input();

            Player.whoWonChanges(whoWon);
            Player.checkWins();
            Player.checkWinsMatch();

        }
        Input.close();
    }
}
