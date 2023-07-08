package termProject_Update;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class FileWrite {
	
	static FileWriter writer;
	static int score;
	static int timeElapsed,leftTime;
	static String input; 
	static int size;
	
	
	
	public static void fileWriter() { 
		
		leftTime = MyFrame.time;
		timeElapsed = FileRead.getTime()- leftTime;
		String hangmanString = MyFrame.temp;
		size = hangmanString.length();
		
		try(FileWriter file = new FileWriter("userInfos.txt",true)){
        input = JOptionPane.showInputDialog("Please enter user name: ");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm.ss");
        Date date = new Date();
        BufferedWriter output = new BufferedWriter(file);
         output.write(input+","+ leftTime+","+dateformat.format(date)+","+ hangmanString+ ","+ size+ "," +timeElapsed+"\n");
         output.close();
        }

        catch (Exception e) {
          e.getStackTrace();
        }
		
		try
		(FileWriter file2 = new FileWriter ("scores.txt",true)){
			
			BufferedWriter output2 = new BufferedWriter(file2);
		     output2.write(input + "," + calculateScore() +"\n");
		     output2.close();
		} catch (IOException e) {
			
	
			e.printStackTrace();
		}
}

	public static int calculateScore() {
		
		
		score= size*leftTime*2;
		
		return score;
	}
	
	
}
