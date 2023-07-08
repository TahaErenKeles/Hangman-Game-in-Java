package termProject_Update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ButtonClass {
	
	static int i;
	static int falseCounter=1;
	static boolean falseCheck;
	static int trueCounter;
	
	MyFrame jFrame;
	
	
	public ButtonClass(String str1 , JButton[] btnArray, JTextField[] txtArray,MyFrame frame) {
	  	jFrame = frame;
	  	
		for(i=0;i<26;i++) {
			
			btnArray[i].addActionListener(new ActionListener() {
				
				
				JButton current = btnArray[i];
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					falseCheck = true;
					for(int k=0; k<str1.length();k++) {
					
					if(current.getText().equals(str1.charAt(k)+"")) {
					txtArray[k].setText(current.getText());
						falseCheck=false;
						current.setEnabled(false);
						trueCounter++;
						if(trueCounter== str1.length()) {
							MyFrame.youWin();
						}
						
						
						}
					
					}
					if(falseCheck) {
						falseCounter++;
						current.setEnabled(false);
						ImageIcon img = new ImageIcon("./img/" +falseCounter+".png");
						jFrame.l1.setIcon(img);
						if(falseCounter==7) {
							MyFrame.youLose();
							for(i=0;i<26;i++) {
								btnArray[i].setEnabled(false);
							}
						}
					}
					
				
				}
				
				
			});
			
		}
		
	}
	

	
}
