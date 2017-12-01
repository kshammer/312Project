import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPU {
    private Process current = null;
    public Clock cpuTime = new Clock();
    private int IOCycles;
    private randomIO randomIO = new randomIO();
    private IOBurst burst = new IOBurst();
    private boolean first = true;


    public CPU(){


    }
    public boolean checkFirst(){
        return first;
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
    public String Execute(){

        String command = current.getNextCommand();
        System.out.println("THIS IS THE COMMAND " + command);
        String pattern = "(CALCULATE.\\d+)";
        String pattern2 = "(OUT)";
        String pattern3 = "(EXE)";
        Pattern q = Pattern.compile(pattern2);
        Matcher n = q.matcher(command);
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(command);
        Pattern z = Pattern.compile(pattern3);
        Matcher y = z.matcher(command);

        if(y.find()){
            //change what is on and set proccess to exit
            current.setState(State.EXIT);
            return "done";
        }else if (command.equals("IO")) {//do IO Stuff
            current.setState(State.WAIT);
            current.doingIO();
            IOCycles += burst.genNumber();
            return "IO";

        }
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
        else if(command.equals("YIELD")){
            // do yeild stuff
            return "YIELD";
        }
        else if(n.find()){
            // Print something out to console

            return command;


        }else{
            System.out.println("Command could not be parsed. ");
        }
        return null;
    }
    public void removeCurrentProcess(){
        this.current = null;
    }
    public String Cycle(){
        cpuTime.advanceTick();
        current.setRunTime();
       if(randomIO.check()){
            IOCycles += burst.genNumber();
            current.doingIO();
        }
        if(IOCycles != 0){
            IOCycles--;
        }
        else {

            return Execute();
        }
        return null;
    }
    public Process Swap(Process p){
        Process Holder = this.current;
        this.current = p;
        return Holder;
    }

}
