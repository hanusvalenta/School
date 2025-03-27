public class Student {
    private int id = 1;
    private int pole[];

    public int getId() {
        if(id >= 10) {
            return id;
        }
        else return 0;
    }

    private boolean isOK(int pozici) {
        if (pozici >= 0 && pozici < 10) return true;
        return false;
    }

    public boolean setId(int id, int pozici) {
        if(isOK(pozici)) {
            this.pole[pozici] = id;
            return true;
        }
        return false;
    }
}
