import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kyleTest {
    public static void main(String[] args){
        OS test = new OS();
        // test.LOAD("kek");
        test.LOAD("2EX");
        test.LOAD("8RW");
        System.out.println("THIS IS THE QUEUE");
        for(int i = 0; i < test.scheduler.getReadyQueue().size(); i++){

            System.out.println(test.scheduler.getReadyQueue().get(i).getName());
        }

        test.EXE(24);
        System.out.println("THIS IS THE QUEUE");
        for(int i = 0; i < test.scheduler.getReadyQueue().size(); i++){

            System.out.println(test.scheduler.getReadyQueue().get(i).getName());
        }

        test.EXE(24);
        System.out.println("THIS IS THE QUEUE");
        for(int i = 0; i < test.scheduler.getReadyQueue().size(); i++){

            System.out.println(test.scheduler.getReadyQueue().get(i).getName());
        }


        test.EXE(24);
        System.out.println("THIS IS THE QUEUE");
        for(int i = 0; i < test.scheduler.getReadyQueue().size(); i++){

            System.out.println(test.scheduler.getReadyQueue().get(i).getName());
        }

        test.EXE(24);
        System.out.println("THIS IS THE QUEUE");
        for(int i = 0; i < test.scheduler.getReadyQueue().size(); i++){

            System.out.println(test.scheduler.getReadyQueue().get(i).getName());
        }

    }
    public static void printQueue(ArrayList<Process> p){
        System.out.println("THIS IS THE QUEUEE");
        for(int i = 0; i< p.)
    }
}
