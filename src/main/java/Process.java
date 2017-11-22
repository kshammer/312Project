import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

enum State{
    NEW, RUNNING, WAITING, READY, TERMINATED
}
public class Process {
    private State state;
    private int arrival;
    private int size;
    private int nextCommand = 0;
    private String name;
    private ArrayList<String> commands = new ArrayList<String>();

    public Process(){

    }

    public Process(String name, int size, int arrival, ArrayList<String> commands){
        setName(name);
        setArrival(arrival);
        setSize(size);
        setCommands(commands);

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
        return commands.get(nextCommand);
    }
    public String getName(){
        return this.name;
    }



}
