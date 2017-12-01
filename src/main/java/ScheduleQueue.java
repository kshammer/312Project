import java.util.ArrayList;
import java.util.Collections;


public class ScheduleQueue {
    //Divide into seperate parts for A/B grade
    private int totalMem = 4096;
    public ArrayList<Process> readyQueue = new ArrayList<Process>();
    public ArrayList<Process> waitQueue = new ArrayList<Process>();
    public ArrayList<Process> jobQueue = new ArrayList<Process>();


    public ScheduleQueue(){

    }
    public void enqueueReady(Process process){
        if (process.getSize() < this.totalMem) {
            process.setState(State.READY);
            readyQueue.add(process);
            this.totalMem -= process.getSize();
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
    public Process dequeueReady(){

        return readyQueue.remove(0);

    }
    public int getTotalMem(){
        return this.totalMem;
    }
    public void setJobQueue(ArrayList<Process> p){
        jobQueue.clear();
        for(int i = 0; i < p.size(); i++){
            jobQueue.add(p.get(i));
        }
    }
    public void updateQueue(){
        if(readyQueue.size() == 0){
            if(waitQueue.size() != 0){
                enqueueReady(dequeueWaiting());
            }
        }

    }
    public void reset(){
        this.totalMem = 4096;
        readyQueue.clear();
        waitQueue.clear();
    }
}
