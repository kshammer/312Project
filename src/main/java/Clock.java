public class Clock {
    private int tick = 0;

    public Clock(){

    }

    public  int getTick(){
        return this.tick;
    }
    public  void advanceTick(){
        this.tick ++;
    }
    public  void reset(){
        this.tick = 0;
    }
}
