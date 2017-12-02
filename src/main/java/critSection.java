import java.util.Random;

public class critSection {

    // 1 in 100 chance to enter critical section
    public static boolean check()
    {
        Random ran = new Random();
        int j = ran.nextInt(100);

        if(j == 1)
        {
            System.out.println("ENTERED CRITICAL SECTION");
            return true;
        }

        return false;
    }
}
