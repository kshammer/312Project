public class Clock {
    private static int tick = 0;

    public Clock(){

    }

    public int getTick(){
        return tick;
    }
    public void advanceTick(){
        tick++;
    }
}
