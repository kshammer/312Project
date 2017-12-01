import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class OS {

    public ArrayList<Process> processes = new ArrayList<Process>();
    public CPU cpu = new CPU();
    public Scheduler scheduler = new Scheduler();
    public OS(){
        getCommands();
        scheduler.programs.setJobQueue(processes);
    }
    public void runCPU(){

        GUITEST.update();

        if(cpu.checkFirst()){

            cpu.setCurrentProcess(scheduler.getNextProcess());

            cpu.setFirst(false);
        }
        if(scheduler.getQuantum() == 0){
            scheduler.resetQuantum();
            scheduler.programs.enqueueReady(cpu.Swap(scheduler.getNextProcess()));
        }
        //scheduler.programs.enqueueReady(cpu.getCurrentProcess());
        String update = cpu.Cycle();

        if(update.equals("IO")){

        }
        if(update.equals("done")){
            System.out.println("A PROCESS HAS FINISHEDJ");
            cpu.Swap(scheduler.getNextProcess());
            scheduler.resetQuantum();
        }
        if(update.equals("YIELD")){
            scheduler.programs.enqueueReady(cpu.Swap(scheduler.getNextProcess()));
            scheduler.resetQuantum();
        }
        if(update != null){
            if(update.contains("OUT")){
                GUITEST.output(update);
            }


        }
        if(scheduler.getQuantum() > 0){
            scheduler.updateQuantum();
        }


    }
    public void EXE(int amount){
        for(int i = 0; i < amount; i++){
            runCPU();
        }
    }
    public void EXE(){
        while(true){
            runCPU();
        }
    }
    public void LOAD(String process){
        for(int i = 0; i < processes.size(); i++){
            if(process.equals(processes.get(i).getName())){
                processes.get(i).setArrival(Clock.getTick());
                scheduler.programs.enqueueReady(processes.get(i));

                break;
            }
        }

    }
    public ScheduleQueue PROC(){
        return this.scheduler.programs;
    }
    public int MEM(){
        return this.scheduler.programs.getTotalMem();
    }
    public void RESET(){
        Clock.reset();
        cpu.removeCurrentProcess();
        scheduler.clean();

    }
    public void EXIT(){
        System.exit(0);
    }

    //loads all the Programs in programs folder into process objects
    public void getCommands(){
        //goes into the programs folder
        File folder = new File("./programs");
        if (folder == null){
            throw new IllegalArgumentException("No programs to run or no programs folder");
        }

        //puts all the files into an array
        File[] listOfFiles = folder.listFiles();
        //for each file
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String name = file.getName();
                String[] nameList = name.split("\\.");
                if(!nameList[1].equals("txt")){
                    System.out.println("Program file not named correctly");
                }
                else{
                    //creates holder process
                    Process holder = new Process();
                    holder.setName(nameList[0]);
                    //loads everything in the file
                    try {
                        //need to create error checking for this
                        Scanner readFile = new Scanner(file);
                        ArrayList<String> commands = new ArrayList<String>();
                        holder.setSize(readFile.nextInt());
                        while(readFile.hasNextLine()){
                            // check to make sure all the commands are valid
                            String nextCommand = readFile.nextLine();

                            commands.add(nextCommand);
                        }
                        commands.remove(0);
                        holder.setCommands(commands);
                    }
                    catch(Exception E){
                        System.out.println("Failed to create process");
                        E.printStackTrace();
                    }
                    processes.add(holder);
                }
            }
        }
    }
}