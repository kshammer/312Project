import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

enum State{
    NEW, RUNNING, WAITING, READY, TERMINATED
}
public class Process {
    private State state;
    private int arrival;
    private int size;
    private int lastCommand = 0;
    private String name;
    private ArrayList<String> commands = new ArrayList<String>();

    public Process(File commands){
        if (commands.exists()) {
            this.state = State.NEW;
            parseCommands(commands);
        }
        else{
            throw new IllegalArgumentException("The file does not exist");
        }

    }
    private void parseCommands(File commands){
        //file name is the name of the process
        this.name = commands.getName();
        try {
            Scanner reader = new Scanner(commands);
            //need to setup error checking for this
            this.size = reader.nextInt();
            while(reader.hasNext()){
                //needs error checking to see if last command is EXE
                this.commands.add(reader.next());
            }

        }
        catch(Exception e){
            System.out.println("Couldn't open file");
            e.printStackTrace();
        }
    }


}
