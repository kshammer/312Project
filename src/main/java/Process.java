import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

enum State{
    NEW, READY, RUN, WAIT, EXIT,
}
enum Priority{
    HIGH, MED, LOW
}
public class Process {
    private State state;
    private Priority pri;
    private int arrival;
    private int size;
    private String name;
    private int runTime;
    private ArrayList<String> commands = new ArrayList<String>();
    private boolean critical = false;

    public Process(){

    }

    public Process(String name, int size, int arrival, ArrayList<String> commands){
        setName(name);
        setArrival(arrival);
        setSize(size);
        setCommands(commands);
        this.state = this.state.NEW;


    }
    public void setPriority(Priority pri){
        this.pri = pri;
    }
    public Priority getPriority(){
        return this.pri;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setArrival(int time){
        if(time > 0){
            this.arrival = time;
        }
        else{
            throw new IllegalArgumentException("time has to be greater than zero");
        }
    }
    public void setCommands(ArrayList<String> coolCommands){
        //copies by value should only be called once per process
        commands.addAll(coolCommands);
    }
    public void setSize(int size){
        if(size > 0){
            this.size = size;
        }
        else{
            throw new IllegalArgumentException("Size has to be greater than zero");
        }
    }
    public int getArrival(){
        return this.arrival;
    }
    public int getSize(){
        return this.size;
    }
    public String getNextCommand(){
        if(commands.size() > 0) {
            return commands.remove(0);
        }else{
            return "done";
        }
    }
    public String getName(){
        return this.name;
    }
    public void caclulateRunTime(){
        if(!commands.isEmpty()){
            for (String command1 : commands) {
                String[] command = command1.split("\\s+");
                if (command[0].equals("CALCULATE")) {
                    runTime += Integer.parseInt(command[1]);
                }
            }
        }
    }
    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return this.state;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
    public boolean getCritical(){
        return this.critical;
    }
}
