import java.util.ArrayList;
import java.util.Collections;


public class ScheduleQueue {
    private int totalMem = 4096;
    private ArrayList<Process> readyQueue = new ArrayList<Process>();
    private ArrayList<Process> waitQueue = new ArrayList<Process>();


    public ScheduleQueue(){

    }
    public void enqueueReady(Process process){
        if (process.getSize() < totalMem) {
            process.setState(State.READY);
            readyQueue.add(process);
            totalMem -= process.getSize();
        } else {
            enqueueWaiting(process);
        }

    }
    public void enqueueWaiting(Process process) {
        waitQueue.add(process);
    }
    public Process dequeueWaiting() {
        return waitQueue.remove(0);
    }
    public Process dequeueReady(){ return waitQueue.remove(0);

    }

}
