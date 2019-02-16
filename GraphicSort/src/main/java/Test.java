import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {
	
	private static Sorter sorter;
	private static int numberOfElements = 20;
	private static Semaphore semaphore;
	
	public static void main(String args[]) {
		
		semaphore = new Semaphore(1,true);
		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		JFrame frame = new JFrame();
		build(frame);
		
		Thread thread = new Thread(sorter);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	private static void build(JFrame frame) {
		
		frame.setSize(400,400);
		
		JButton run,stop;
		run = new JButton("Run");
		stop = new JButton("Stop");
		run.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				semaphore.release();
			}

		});
		
		stop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					semaphore.acquire();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.setSize(400,400);
		buttons.setBackground(new Color(64, 106, 173));
		buttons.add(run);
		buttons.add(stop);
		
		MyJPanel graph = new MyJPanel(numberOfElements);
		
		frame.add(buttons,BorderLayout.NORTH);
		frame.add(graph,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		chooseSort(graph);
		graph.setNumbers(sorter.getNumbers());
	}
	
	private static void chooseSort(MyJPanel graph) { // number of elements to sort
		
		sorter = new BubbleSorter(numberOfElements,graph,semaphore);
		
	}
}
