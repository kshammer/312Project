import java.util.ArrayList;
import java.util.Collections;


public class ScheduleQueue {
    private int totalMem = 4096;
    private ArrayList<Process> readyQueue = new ArrayList<Process>();
    private ArrayList<Process> waitQueue = new ArrayList<Process>();
    private ArrayList<Process> ioQueue = new ArrayList<Process>();

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
    public void enqueueIO(Process process) {
        ioQueue.add(process);
    }
    public Process dequeueWaiting() {
        return waitQueue.remove(0);
    }
    public Process dequeueIO(){
        return ioQueue.remove(0);
    }
    public Process dequeueReady(){
        // rotate queue, round robin (only if there are unblocked processes)
        int unblocked = 0;
        for (Process p : this.readyQueue) {
            if (p.getState() != State.BLOCKED) {
                unblocked++;
            }
        }
        if (unblocked > 0) {
            Collections.rotate(this.readyQueue, 1);
        }

        // return process
        for (Process p : this.readyQueue) {
            if (p.getState() != State.BLOCKED) {
                //freeMemory += p.getSize();
                return p;
            }
        }

        return null;
    }

}
