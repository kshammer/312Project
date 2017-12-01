import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        //System.out.println("Hello");
        ArrayList<String> test = new ArrayList<String>();
        String test1 = "Hello";
        test.add(test1);
        System.out.println(test);
        test1 = "goodbye";
        System.out.println(test);
        OS testOS = new OS();
        System.out.println(testOS.processes.toString());

        OS test2 = new OS();
        System.out.println(test2.processes.toString());

    }
}
