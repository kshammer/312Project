import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kyleTest {
    public static void main(String[] args){
        OS test = new OS();
        // test.LOAD("kek");
        test.LOAD("2EX");
        test.LOAD("8RW");
        test.LOAD("kek");
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());
        test.EXE(24);
        printQueue(test.scheduler.getReadyQueue());


    }
    public static void printQueue(ArrayList<Process> p){
        System.out.println("THIS IS THE QUEUEE");
        for(int i = 0; i< p.size(); i++){
            System.out.println(i + " " + p.get(i).getName());
        }
    }
}
