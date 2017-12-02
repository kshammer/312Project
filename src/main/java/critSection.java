import java.util.Random;

public class critSection {

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
