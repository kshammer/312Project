import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPU {
    public Process current = null;
    private int IOCycles;
    public static Clock cpuTime = new Clock();
    private randomIO randomIO = new randomIO();
    private IOBurst burst = new IOBurst();
    private boolean first = true;


    public CPU(){


    }
    public boolean checkEmpty(){
        return first;
    }
    //checks to see if there is a program on the cpu
    public void isEmpty(){
        this.first = true;
    }
    public void setFirst(boolean b){
        this.first = b;
    }
    public Process getCurrentProcess(){
        return this.current;
    }
    public void setCurrentProcess(Process proc){
        this.current = proc;
    }
    //CPU does one Cycle
    public String Execute(){
        //set process to run
        current.setState(State.RUN);
        if(critSection.check()){
            //generates critical section
            current.genCriticalSection();
        }

        String command = current.getNextCommand();
       // System.out.println("THIS IS THE COMMAND " + command);
        //regex to check commands
        String pattern = "(CALCULATE.\\d+)";
        String pattern2 = "(OUT)";
        String pattern3 = "(EXE)";
        Pattern q = Pattern.compile(pattern2);
        Matcher n = q.matcher(command);
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(command);
        Pattern z = Pattern.compile(pattern3);
        Matcher y = z.matcher(command);
        // if it finds EXE
        if(y.find()){
            //change what is on and set proccess to exit
            System.out.println("THIS PROCESS SHOULD BE DONE");
            current.setState(State.EXIT);
            return "done";
        //if it finds IO
        }else if (command.equals("IO")) {//do IO Stuff
            current.setState(State.WAIT);
            current.doingIO();
            IOCycles += burst.genNumber();
            return "IO";

        }
        //if it finds calc
        else if(m.find()){
            StringBuilder sb = new StringBuilder();
            String[] commands = command.split("\\s+");
            int calc = Integer.parseInt(commands[1]);
            if(calc > 0){
                calc--;
                sb.append(commands[0]);
                sb.append(" ");
                sb.append(calc);
                current.addCommand(sb.toString());
            }
            return "CALCULATE";

        }
        //if it finds yeild
        else if(command.equals("YIELD")){
            // do yeild stuff
            return "YIELD";
        }
        //if it finds out
        else if(n.find()){
            // Print something out to console

            return command;


        }else{
            System.out.println("Command could not be parsed. " + command + " " + current.getName());
        }
        return null;
    }
    public void removeCurrentProcess(){
        this.current = null;
    }
    //runs a cpu cycle
    public String Cycle(){
        cpuTime.advanceTick();
        current.setRunTime();
        if(current.getState() != State.WAIT) {
            if (randomIO.check()) {
                IOCycles += burst.genNumber();
                current.doingIO();
            }

        }
        if (IOCycles != 0) {
            IOCycles--;
        }
        else {

            return Execute();
        }
        return "WAITING";
    }
    //swaps what is on processor
    public Process Swap(Process p){
        //System.out.println("SWAPPING IN " + p.getName());
        Process Holder = this.current;
        this.current = p;
        return Holder;
    }

}
