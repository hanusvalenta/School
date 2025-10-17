public class MojeVyjimka extends RuntimeException {
    String text;

    public MojeVyjimka(String text) {
        this.text = text;
    }

    public String toString() {
        return text;
    }
}
