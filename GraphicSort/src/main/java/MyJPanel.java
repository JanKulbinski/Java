import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyJPanel extends JPanel {
	
	private int [] numbers;
	private int buffor = 40;			//specifies how far from frame's bounds rectangles are drawn
	private int compared1, compared2; // 2 rectagles which are at the moment being compared
	
	MyJPanel(int numberOfElements) { // number of elements to sort
		
		super();
		setLayout(new FlowLayout());
		setSize(400,400);
		setBackground(new Color(186, 196, 211));
		numbers = new int[numberOfElements];
		compared1 = -1;
		compared2 = -1;
	}
	

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		int width = ((int) getSize().getWidth() - buffor) / numbers.length;
		
		for (int i = 0; i < numbers.length; i++) {
			int height = (int) ((numbers[i] * getSize().getHeight() - buffor) / 100);
			int x = i * width + buffor / 2;
			int y = (int) getSize().getHeight() - height;
			
			if (i == compared1 || i == compared2) {
				g.setColor(new Color(255, 51, 51));
				g.fillRect(x, y, width, height);
				g.setColor(Color.BLACK);
			}
			
			g.drawRect(x, y, width, height);
		}
		
		
	}
	
	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}
	
	public void setCompared(int compared1, int compared2) {
		this.compared1 = compared1;
		this.compared2 = compared2;
	}
}
