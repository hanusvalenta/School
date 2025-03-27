public class Faktorial {
    int makeFactorial(int y){
        int z = 0;

        for(int i = y; i > 0; i--){
            z = y * i;
        }

        return z;
    }
}
