import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class jobfiles {
	
	public static void createJobFiles() {
		
		try
		{
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			StringBuilder filename = new StringBuilder();
			
			Random rand = new Random();
			
			while (filename.length() < 3) {
	        int index = (int) (rand.nextFloat() * characters.length());
	        filename.append(characters.charAt(index));
			}
			
			int  lines = rand.nextInt(300) + 1;
			PrintWriter writer = new PrintWriter("..\\312Project\\programs\\" + filename +".txt");
			writer.println(lines);
			
			for(int i = 0; i < lines; i++) {
				int choice = rand.nextInt(4);
				if(choice == 0)
				{
					writer.println("CALCULATE " + (rand.nextInt(100)+1));
				}
				else if(choice == 1)
				{
					writer.println("IO");
				}
				else if(choice == 2)
				{
					writer.println("YIELD");
				}
				else if(choice == 3)
				{
					writer.println("OUT \"WORDS\"");
				}
			}
			writer.print("EXE " + filename);
			
			writer.close();
		}
		catch(FileNotFoundException e){
			
		}
	}
/*
	public static void main(String[] args){
		createJobFiles();
	}
*/
}
