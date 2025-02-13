public class Hrac {
    private String name;
    private int points;
    private int games;
    private int sets;
    private boolean advantage;

    public Hrac(String name) {
        this.name = name;
        this.points = 0;
        this.games = 0;
        this.sets = 0;
        this.advantage = false;
    }

    public String getName() { return name; }
    public int getPoints() { return points; }
    public int getGames() { return games; }
    public int getSets() { return sets; }
    public boolean hasAdvantage() { return advantage; }

    public void addPoint() { points++; }
    public void winGame() { games++; }
    public void winSet() { sets++; }

    public void setAdvantage(boolean advantage) { this.advantage = advantage; }

    public void resetPoints() {
        points = 0;
        advantage = false;
    }

    public void resetGames() { games = 0; }
}