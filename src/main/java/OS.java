import java.io.File;
import java.util.ArrayList;

public class OS {

    ArrayList<Process> processes = new ArrayList<Process>();
    public OS(){


    }

    public void getCommands(){
        File folder = new File("./programs");
        if (folder == null){
            throw new IllegalArgumentException("No programs to run");
        }
        //System.out.println(folder.listFiles());
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String name = file.getName();
                String[] nameList = name.split(".");
                if(!nameList[1].equals("txt")){
                    System.out.println("Program file not in correct format");
                }
                else{
                    Process holder = new Process();
                    holder.setName(nameList[0]);

                }


            }
        }
    }


}
