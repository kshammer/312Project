import java.util.ArrayList;

public class Scheduler {
    private int Quantum = 25;
    //holds different queues
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
    //returns next process in the queue
    public Process getNextProcess(){
        //if there is something in ready queue use that
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
    //adds a process to the queueu
    public static void addProcess(Process p){
        p.setState(State.READY);
        programs.updateQueue();
        programs.enqueueReady(p);
    }
    public static ArrayList<Process> getReadyQueue(){
        return programs.readyQueue;
    }
    
    public static ArrayList<Process> getWaitQueue(){
        return programs.waitQueue;
    }

    //for reset command
    public void clean(){
        resetQuantum();
        programs.reset();
    }



}
