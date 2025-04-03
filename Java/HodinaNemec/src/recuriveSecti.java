public class recuriveSecti {
    int scitej(int y) {
        int out;

        if (y == 0) {
            return 0;
        }

        out = y % 10;
        out = out + scitej(y / 10);

        return out;
    }
}
