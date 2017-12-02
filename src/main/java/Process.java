import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//State process can be in
enum State{
    NEW, READY, RUN, WAIT, EXIT,
}
//sets the priority
enum Priority{
    HIGH, MED, LOW
}
public class Process {
    //class variables
    private State state = State.NEW;
    private Priority pri;
    private int arrival;
    private int size;
    private String name;
    private int runTime;
    public ArrayList<String> commands = new ArrayList<String>();
    private boolean critical = false;
    private int IO = 0;
    private int totalRunTime;
    private ArrayList<Process> family = new ArrayList<Process>();

    public Process(){

    }

    public Process(String name, int size, int arrival, ArrayList<String> commands){
        setName(name);
        setArrival(arrival);
        setSize(size);
        setCommands(commands);
        this.state = this.state.NEW;


    }
    //Sends the process into a critical section
    public void genCriticalSection(){
        if(!family.isEmpty()){
            for(int i =0; i < family.size(); i++){
                if(!this.name.equals(family.get(i).getName()))
                    family.get(i).addCommand("YIELD");
            }
        }

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
        this.arrival = time;
    }
    //Calculates the runtime of the process
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
    //Sets the commands
    public void setCommands(ArrayList<String> coolCommands){
        //copies by value should only be called once per process
        this.commands.clear();
        for(int i = 0; i < coolCommands.size(); i++){

            this.commands.add(coolCommands.get(i));

        }
        calcRuntime();
    }
    public void addCommand(String command){
        commands.add(0, command);
    }
    public void addCommandToEnd(String command){
        commands.add(command);
    }

    public ArrayList<String> getCommands() {
        return commands;
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

        if(this.commands.size() > 0) {

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
    public void setRunTime(){
        this.runTime++;
    }
    //splits the proccess into different threads
    public Process[] thread(Process p){
        Process holder = new Process();
        holder.setSize(p.getSize());
        holder.setName(p.getName());
        holder.setCommands(p.getCommands());
        holder.setState(p.getState());
        Process process1 = new Process();
        Process process2 = new Process();
        Process process3 = new Process();
        process1.setName(p.getName() + "child1");
        process1.setState(p.getState());
        process1.setSize(p.getSize() /4);
        process2.setName(p.getName() + "child2");
        process2.setState(p.getState());
        process2.setSize(p.getSize()/4);
        process3.setName(p.getName() + "child3");
        process3.setState(p.getState());
        process3.setSize(p.getSize()/4);
        holder.setSize(p.getSize()/4);
        for(int i = 0; i < 15; i++){
            process1.addCommand(holder.getNextCommand());
            process2.addCommand(holder.getNextCommand());
            process3.addCommand(holder.getNextCommand());
        }
        process1.addCommandToEnd("EXE");
        process2.addCommandToEnd("EXE");
        process3.addCommandToEnd("EXE");
        Process[] familys = new Process[4];
        familys[0] = holder;
        familys[1] = process1;
        familys[2] = process2;
        familys[3] = process3;
        for(int z = 0; z < familys.length; z++){
            p.family.add(familys[z]);
            process1.family.add(familys[z]);
            process2.family.add(familys[z]);
            process3.family.add(familys[z]);
        }
        return familys;


    }


}
