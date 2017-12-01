import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class kyleTest {
    public static void main(String[] args){
        String line = "CALC 72";
        String pattern = "(CALC.\\d+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(line);
        if(m.find()){
            System.out.println(m.group(0));
        }
    }
}
