import java.util.ArrayList;

public class Scheduler {
    private int Quantum = 25;
    private static ScheduleQueue programs = new ScheduleQueue();

    public Scheduler(){

    }
    public int getQuantum(){
        return this.Quantum;
    }
    public void resetQuantum(){
        this.Quantum = 25;
    }
    public void updateQuantum(){
        this.Quantum--;
    }
    public Process getNextProcess(){
        if(programs.readyQueue.size() != 0){
            return programs.dequeueReady();
        }else{
            programs.updateQueue();
            return programs.dequeueReady();
        }
    }
    public static void addProcess(Process p){
        p.setArrival(Clock.getTick());
        programs.enqueueReady(p);
    }
    public static ArrayList<Process> getReadyQueue(){
        return programs.readyQueue;
    }



}
