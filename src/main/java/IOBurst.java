import java.util.Random;

public class IOBurst {
    //gens io burst of 25 or 50 cycles
    private int min = 25;
    private int max = 50;
    public IOBurst(){

    }

    public int genNumber(){
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
