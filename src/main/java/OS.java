import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class OS {

    public ArrayList<Process> processes = new ArrayList<Process>();
    public CPU cpu = new CPU();
    public Scheduler scheduler = new Scheduler();
    public OS(){
        getCommands();
        scheduler.programs.setJobQueue(processes);
    }
    public void runCPU(){
        if(cpu.checkFirst()){
            //System.out.println("ADDING FIRST PROCESS");
            cpu.setCurrentProcess(scheduler.getNextProcess());
            //System.out.println("THIS IS THE CURRENT PROCESS ON CPU" + cpu.getCurrentProcess().getName());
            cpu.setFirst(false);
        }
        String update = cpu.Cycle();
        System.out.println("THIS IS UPDATE " + update);
        if(update.equals("done")){
            cpu.Swap(scheduler.getNextProcess());
            scheduler.resetQuantum();
        }
        if(update.equals("YIELD")){
            cpu.Swap(scheduler.getNextProcess());
            scheduler.resetQuantum();
        }
        if(update != null){
            //print to screen
            System.out.println(update);

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
    public void LOAD(String process){
        for(int i = 0; i < processes.size(); i++){
            if(process.equals(processes.get(i).getName())){
                scheduler.programs.enqueueReady(processes.get(i));
                System.out.println("ADDING THIS TO READ "  + processes.get(i).getName());
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
        //System.out.println(folder.listFiles());
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
                            //System.out.println(nextCommand);
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
