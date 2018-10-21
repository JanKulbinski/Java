package mypackage;

import java.util.concurrent.TimeUnit;
import javax.swing.*;

/**
 * starts game
 * @author Jan Kulbiński
 */
public class GameTest {

	
	public static void main(String[] args) {
		
		JFrame frame;
		GamePanel gamePanel;
		
		frame= new JFrame("SNAKE");		
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
				
		int answer=0;
		do {
			gamePanel=new GamePanel();
			frame.add(gamePanel);
			frame.addKeyListener(gamePanel);
			gamePanel.running();
		
			try {
				TimeUnit.SECONDS.sleep(1);
			}
			
			catch (InterruptedException e) {
				e.printStackTrace();
			}
	    
			answer = JOptionPane.showOptionDialog(frame, "Do you want to try again?","One more chance", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		} while(answer==0);
	}
}


