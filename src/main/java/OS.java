import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class OS {

    public ArrayList<Process> processes = new ArrayList<Process>();
    public static CPU cpu = new CPU();
    public Scheduler scheduler = new Scheduler();
    public OS(){
        getCommands();
        scheduler.programs.setJobQueue(processes);
    }
    public void runCPU(){


        if(cpu.checkEmpty()){

            cpu.setCurrentProcess(scheduler.getNextProcess());

            cpu.setFirst(false);
        }
        if(scheduler.getQuantum() == 0){
            scheduler.resetQuantum();
            if(scheduler.getReadyQueue().isEmpty()){
                scheduler.addProcess(cpu.getCurrentProcess());
            }else{

                scheduler.addProcess(cpu.Swap(scheduler.getNextProcess()));
            }

        }
        //scheduler.programs.enqueueReady(cpu.getCurrentProcess());
        String update = cpu.Cycle();

        if(update.equals("IO")){

        }
        if(update.equals("done")){
            System.out.println("A PROCESS HAS FINISHED");
            if(scheduler.programs.readyQueue.isEmpty()){

            }else{
                cpu.Swap(scheduler.getNextProcess());
            }

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
            if(scheduler.programs.readyQueue.isEmpty() && cpu.getCurrentProcess().getState() == State.EXIT){
                cpu.isEmpty();
                break;
            }
        }
    }
    public void EXE2(){
        while(true){
            if(scheduler.programs.readyQueue.isEmpty() && cpu.getCurrentProcess().getState() == State.EXIT){
                cpu.isEmpty();
                break;
            }
            runCPU();
        }
    }
    public void LOAD(String process){
        for(int i = 0; i < processes.size(); i++){
            if(process.equals(processes.get(i).getName())){
                if(processes.get(i).commands.size() > 100){
                    Process[] family = processes.get(i).thread(processes.get(i));
                    for(int p = 0; p < family.length; p++){
                        family[p].setArrival(cpu.cpuTime.getTick());
                        System.out.println("THIS IS THE TICK " + family[p].getArrival());
                        scheduler.programs.enqueueReady(family[p]);
                    }
                }
                else{
                    processes.get(i).setArrival(cpu.cpuTime.getTick());
                    System.out.println("THIS IS THE TICK " + processes.get(i).getArrival());
                    scheduler.programs.enqueueReady(processes.get(i));

                }



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
        cpu.cpuTime.reset();
        processes.clear();
        cpu.removeCurrentProcess();
        scheduler.clean();
        getCommands();

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