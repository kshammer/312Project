public class Clock {
    private static int tick = 0;

    public Clock(){

    }

    public static int getTick(){
        return tick;
    }
    public static void advanceTick(){
        tick++;
    }
    public static void reset(){
        tick = 0;
    }
}
