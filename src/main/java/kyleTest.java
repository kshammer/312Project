import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kyleTest {
    public static void main(String[] args){
       OS test = new OS();
       test.LOAD("kek");
       test.LOAD("2EX");
       test.LOAD("8RW");
       test.LOAD("BY5");
       test.LOAD("PF0");
       System.out.println("THIS SHOULD BE THE NAME " + test.scheduler.viewNextProcess().getName());
       System.out.println("THIS IS THE TOTAL RUNTIME " + test.scheduler.viewNextProcess().getTotalRunTime());
       test.EXE(500);
       System.out.println(test.scheduler.viewNextProcess().getRunTime());
    }
}
