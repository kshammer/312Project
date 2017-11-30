public class Clock {
    private static int tick = 0;

    public Clock(){

    }

    public static int getTick(){
        return tick;
    }
    public void advanceTick(){
        tick++;
    }
    public static void reset(){
        tick = 0;
    }
}
