package termProject_Update;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class MyFrame {

	static JFrame frame;
	ImageIcon img;
	static JButton[] btnArray;
	JTextField[] txtArray;
	JTextField t1;
	JLabel l1;
    static JLabel status;
    static int time;
    static Timer timer;
    static JLabel countdown;
    static String temp;
    static String check;
    JMenuItem item4;
    JTable table1;
    DefaultTableModel model;
    static int x=0;
    
	static HashMap<String, Integer> map = new HashMap<>();
    static LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    static ArrayList<Integer> list = new ArrayList<>();
    
	
	
	JPanel KBoardLayout;
	
	public static void youWin() {
		check = "win";
		status.setText("<html>Status: <b><font color=\"green\">You WIN!</font></b></html>");
		for(int i=0;i<26;i++) {
			btnArray[i].setEnabled(false);
		}
		
		timer.stop();
		FileWrite.fileWriter();
		
		
	}
	public static void youLose() {
		status.setText("<html>Status: <b><font color=\"red\">You LOSE!</font></b></html>");
		for(int i=0;i<26;i++) {
			btnArray[i].setEnabled(false);
		}
		timer.stop();
		FileWrite.fileWriter();
		}
	
	public void CountdownTimer() {
		time = FileRead.getTime();
		
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				time--;
				countdown.setText("Countdown: "+time);
				if(time==0) {
					timer.stop();
					youLose();
					
				}
				
			}
			
		});
	}
	
	public MyFrame() {
		frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("CSE 212 Term Project- Hangman Game");
		frame.setLayout(null);
		
		img = new ImageIcon("./img/1.png");
		btnArray = new JButton[26];
		txtArray = new JTextField[26];
		
		l1 = new JLabel(img);
		l1.setBounds(550, 100, 300, 300);
		
		
			
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		JMenuItem item1 = new JMenuItem("New Game");
		JMenuItem item2 = new JMenuItem("Reset Game");
		JMenuItem item3 = new JMenuItem("Score Table");
		item4 = new JMenuItem("Quit");
		JMenuItem item5 = new JMenuItem("About");
		
		fileMenu.add(item1);
		fileMenu.add(item2);
		fileMenu.add(item3);
		fileMenu.add(item4);
		
		helpMenu.add(item5);
		
		

		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				newGame();
			}
			
		});
		
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
		
		
		item3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 readScore();
			     sortScore();
			     Set<Map.Entry<String, Integer>> entries = sortedMap.entrySet();
			     Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();

			     
			     for (int i = 0; i < 10 && iterator.hasNext(); i++) {
			       Map.Entry<String, Integer> entry = iterator.next();
			       updateBoard(entry.getKey() ,entry.getValue());
			     }
				scoreFrame();
				
			}
			
		});
		
		Object[][] info = {{"", "", ""},{"High Scores", "Name", "Score"}};

		 String[] columnNames = {"","",""};
		 model = new DefaultTableModel(info,columnNames);
		
		
		
		
		item4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
			
		});
		
		item5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Developer Name: Taha Eren Keles\n "
						+ "School Number: 20200702028\n Email: tahaeren.keles@std.yeditepe.edu.tr ");
				
			}
			
		});
		
		item4.setMnemonic(KeyEvent.VK_H);
		item4.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
		fileMenu.add(item4);
		
		/*
		 readScore();
	     sortScore();
	     Set<Map.Entry<String, Integer>> entries = sortedMap.entrySet();
	     Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();

	     
	     for (int i = 0; i < 10 && iterator.hasNext(); i++) {
	       Map.Entry<String, Integer> entry = iterator.next();
	       updateBoard(entry.getKey() ,entry.getValue());
	     }
		*/
		
		
		KBoardLayout = new JPanel();
		KBoardLayout.setBounds(40, 230, 450, 150);
		//KBoardLayout.setBackground(Color.black);
		KBoardLayout.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		int k= 0;
		char c = 'A';
		while(c<='Z') {
		String str= c+"";
		JButton btn = new JButton(str);
		btnArray[k]=btn;
		KBoardLayout.add(btn);
		k++;
		c++;
		}
		
		
		int j= 50;
		temp = FileRead.getName();
		for(k=0; k < temp.length() ;k++) {
			
			t1 = new JTextField();
		    txtArray[k]= t1;
			t1.setBounds(j, 180, 40, 30);
			t1.setEditable(false);
			//TextFieldLayout.add(t1);
			frame.add(t1);
			j= j+45;
			
		
		}
		
		JLabel tip = new JLabel("Tip: " + FileRead.getTip());
		tip.setBounds(50, 40, 500, 20);
		
		JLabel NotinString = new JLabel("Not in string: " + FileRead.getNotinString());
		NotinString.setBounds(50, 90, 200, 20);
		
		status = new JLabel("<html>Status: <b><font color=\"blue\">In Progress</font></b></html>");
		status.setBounds(50, 140, 150, 20);
			
		
			
		
		countdown = new JLabel("Countdown: ");
		countdown.setBounds(250, 140, 200, 20);
		
		CountdownTimer();
		timer.start();
		
		
		
		new ButtonClass(temp, btnArray, txtArray,this);
		System.out.println(temp);
		
		
		
		
		
		frame.add(KBoardLayout);
		frame.setJMenuBar(menuBar);
		frame.add(l1);
		frame.add(NotinString); 
		frame.add(status);
		frame.add(tip);
		frame.add(countdown);
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.setSize(850,500);
	}
	
	
	public void updateBoard(String username, Integer score) {
		
	 	
		x++;
		if(x<=10) {
		  Object[] newRow = {x,username,score};
		  model.addRow(newRow);}
		  
	  

 }
	

	public void readScore() {
		 String line;
		 
		 
		 try {
			 BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));

			
			while ((line = reader.readLine()) != null) {
				
		        String[] parts = line.split(",");
		        String key =  parts[0];
		        Integer value =Integer.parseInt(parts[1]);
		        
		        map.put(key, value);
				
			
			}
			
			
			reader.close();}
			 catch(IOException e){
				 e.printStackTrace();
			    }
		 
		 
	 }
	
	
	static JFrame frame2 = new JFrame();
	
	public void scoreFrame() {
		
		
		frame2.setTitle("High Scores");
		frame2.setDefaultCloseOperation(frame2.DISPOSE_ON_CLOSE);
		frame2.setSize(480,480);
		frame2.setLayout(null);
		frame2.setVisible(true);
		frame2.setResizable(false);
		 
		 table1 = new JTable(model);
		 table1.setOpaque(true);
		 table1.setAutoCreateRowSorter(true);
		 table1.setSize(500,500);
		 table1.setLayout(new FlowLayout(FlowLayout.CENTER));
		 table1.setVisible(true);

		 frame2.add(table1);
	 }
	
	public void sortScore() {
		 
		 for (Map.Entry<String, Integer> entry : map.entrySet()) {
	            list.add(entry.getValue());
	        }

		 
		 for (Map.Entry<String, Integer> entry : map.entrySet()) {
	            list.add(entry.getValue());
	        }
	        Collections.sort(list);
	        Collections.reverse(list);
	        for (int num : list) {
	            for (Entry<String, Integer> entry : map.entrySet()) {
	                if (entry.getValue().equals(num)) {
	                    sortedMap.put(entry.getKey(), num);
	                }
	            }
	        }
		 
	 }
	
	
	
	public void newGame() {
		
		timer.stop();
		frame.dispose();
		ButtonClass.falseCounter=1;
		ButtonClass.trueCounter=0;
		new MyFrame();
	}
	
	

	
}
