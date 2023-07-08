package termProject_Update;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileRead {
	
	static String string;
	static String filePath = "questions.txt";
	static String temp;
	static List<String> strings = new ArrayList<String>();
    static List<String> lines = new ArrayList<String>();
	
	public static void rndLine() {
		 
	        

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	           
	            String line = br.readLine();
	            while (line != null) {
	                lines.add(line);
	                for (String string : line.split(",")) {
	                    strings.add(string);
	                }
	                line = br.readLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        Random random = new Random();
	        String randomLine = lines.get(random.nextInt(lines.size()));
	        temp=randomLine;
	        		
	        
	}
	
	public static String getName() {
//		Random random = new Random();
//		
//		String randomLine =  lines.get(random.nextInt(lines.size()));
		rndLine();
	    String[] name = temp.split("\\,");
	    return name[2];
		
	}
	
	public static String getTip() {
		 
		
		String[] name = temp.split("\\,");
		return name[0];
		
	}
	
	public static String getNotinString() {
		String[] name = temp.split("\\,");
		return name[1];
		
	}
	
	public static int getTime() {
		String[] name = temp.split("\\,");
		return Integer.parseInt(name[3]);
	}
	
	
		

}


