/**
 *
 * @author Aaron Buehne
 */
import java.util.Random;

public class randomIO {

    public randomIO(){

    }
    
    //if return true, generate interrupt
    public boolean check()
    {
        Random ran = new Random();
        int j = ran.nextInt(100);
        
        if(j == 1)
        {
            System.out.println("Hardware interupt generated.");
            return true;
        }
        
        return false;
    }
}
