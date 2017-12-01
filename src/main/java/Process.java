import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private int IO = 0;
    private int totalRunTime;

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
        Clock.getTick();
    }
    public void calcRuntime(){
        String pattern = "(CALCULATE.\\d+)";
        Pattern r = Pattern.compile(pattern);

        for(int i = 0; i < commands.size(); i++){
            Matcher m = r.matcher(commands.get(i));
            if(m.find()){
                String[] command = commands.get(i).split("\\s+");
                int calculations = Integer.parseInt(command[1]);
                totalRunTime += calculations;
            }
        }
    }
    public void setCommands(ArrayList<String> coolCommands){
        //copies by value should only be called once per process
        this.commands.clear();
        for(int i = 0; i < coolCommands.size(); i++){
            //System.out.println("ADDING A COMMAND " +coolCommands.get(i));
            this.commands.add(coolCommands.get(i));
            //System.out.println("CHECKING ADDITION " + this.commands.get(i));
        }
        calcRuntime();
    }
    public void addCommand(String command){
        commands.add(0, command);
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
        System.out.println("FINDING NEXT COMMAND");
        if(this.commands.size() > 0) {
            System.out.println("This is the next command " + this.commands.get(0));
            return this.commands.remove(0);
        }else{
            return "done";
        }
    }
    public String getName(){
        return this.name;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return this.state;
    }
    public void doingIO(){
        this.IO++;
    }
    public int getIO(){
        return this.IO;
    }
    public int getTotalRunTime(){
        return this.totalRunTime;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }
    public boolean getCritical(){
        return this.critical;
    }
    public int getRunTime(){return this.runTime;}
    public void setRunTime(int run){
        this.runTime = run;
    }

}
