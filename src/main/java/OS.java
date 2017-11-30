import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class OS {

    public ArrayList<Process> processes = new ArrayList<Process>();
    public CPU cpu = new CPU();
    public Scheduler scheduler = new Scheduler();
    public OS(){
        getCommands();
        runCPU();
    }
    public void runCPU(){
        while(true){
            cpu.Cycle();
        }
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
                        while(readFile.hasNext()){
                            // check to make sure all the commands are valid
                            commands.add(readFile.next());
                        }
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
