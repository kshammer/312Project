import java.util.ArrayList;

public class ScheduleQueue {
    private int totalMem = 4096;
    private ArrayList<Process> readyQueue = new ArrayList<Process>();
    private ArrayList<Process> waitQueue = new ArrayList<Process>();
    private ArrayList<Process> ioQueue = new ArrayList<Process>();
}
