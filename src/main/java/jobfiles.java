import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class jobfiles {
	
	public static void createJobFiles() {
		
		try
		{
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // List of characters to randomly generate filenames
			StringBuilder filename = new StringBuilder();
			
			Random rand = new Random();
			
			while (filename.length() < 3) { // 3 letter filenames
	        int index = (int) (rand.nextFloat() * characters.length());
	        filename.append(characters.charAt(index));
			}
			
			int  lines = rand.nextInt(300) + 1; // number of lines between 1 and 300
			PrintWriter writer = new PrintWriter("..\\312Project\\programs\\" + filename +".txt");
			writer.println(lines); // adds first line to jobfile
			
			for(int i = 0; i < lines; i++) { // creates random commands for each line in jobfile
				int choice = rand.nextInt(4);
				if(choice == 0)
				{
					writer.println("CALCULATE " + (rand.nextInt(100)+1)); //calculate between 1 and 100
				}
				else if(choice == 1)
				{
					writer.println("IO"); // writes IO command
				}
				else if(choice == 2)
				{
					writer.println("YIELD"); // writes YIELD command
				}
				else if(choice == 3)
				{
					writer.println("OUT \"WORDS\""); // writes OUT command
				}
			}
			writer.print("EXE " + filename); // final line in jobfile is EXE command
			
			writer.close();
		}
		catch(FileNotFoundException e){
			
		}
	}

	public static void main(String[] args){
		createJobFiles(); // Run to create a random single text jobfile
	}

}
