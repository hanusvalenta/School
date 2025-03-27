public class Blok {
    int a,b,c;
    int obvod;

    public Blok(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    boolean isSame(Blok obj) {
        if(this.a == obj.a && this.b == obj.b && this.c == obj.c) {
            return true;
        }
        return false;
    }
}
