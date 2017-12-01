import java.util.ArrayList;

public class Scheduler {
    private int Quantum = 25;
    public static ScheduleQueue programs = new ScheduleQueue();

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
        }else if(programs.waitQueue.isEmpty()) {

            return null;
        }else{
            programs.updateQueue();
            return programs.dequeueReady();
        }
    }
    public Process viewNextProcess(){
        if(programs.readyQueue.size() != 0){
            return programs.readyQueue.get(0);
        }else{
            return null;
        }
    }
    public static void addProcess(Process p){

        programs.enqueueReady(p);
    }
    public static ArrayList<Process> getReadyQueue(){
        return programs.readyQueue;
    }
    
    public static ArrayList<Process> getWaitQueue(){
        return programs.waitQueue;
    }
    
    public void clean(){
        resetQuantum();
        programs.reset();
    }



}
