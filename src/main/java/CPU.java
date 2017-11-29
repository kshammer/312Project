public class CPU {
    private Process current;
    public Clock cpuTime = new Clock();
    private int IOCycles;
    private randomIO randomIO = new randomIO();
    private IOBurst burst = new IOBurst();


    public CPU(){


    }
    public Process getCurrentProcess(){
        return this.current;
    }
    public void setCurrentProcess(Process proc){
        this.current = proc;
    }
    public void Execute(){
        String command = current.getNextCommand();
        if(command.equals("done")){
            //change what is on and set proccess to exit
        }else if (command.equals("IO")) {//do IO Stuff
            current.setState(State.WAIT);

            IOCycles = burst.genNumber();

        }
        else if(command.equals("CALCULATE")){


        }
        else if(command.equals("YIELD")){

            // do yeild stuff
        }
        else if(command.equals("OUT")){
            // Print something out to console
        }else{
            System.out.println("Command could not be parsed. ");
        }
    }
    public Process removeCurrentProcess(){
        return this.current;
    }
    public void Cycle(){
        if
        if(IOCycles != 0){
            IOCycles--;
        }
    }

}
