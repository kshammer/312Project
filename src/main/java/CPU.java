public class CPU {
    private Process current;
    public Clock cpuTime = new Clock();

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
    }

}
