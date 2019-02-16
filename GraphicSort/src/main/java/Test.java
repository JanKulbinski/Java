import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Test {
	
	public enum State {On, Off};
	
	private static State state;
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
		
		state = State.Off;
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				if(state != State.On) {
					semaphore.release();
					state = State.On;
				}
			}

		});
		
		stop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(state == State.On) {
					try {
						state = State.Off;
						semaphore.acquire();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		Color buttonsPanelColor = new Color(64, 106, 173);
		JSlider slider = new JSlider(JSlider.HORIZONTAL,1,20,3);
		slider.setBackground(buttonsPanelColor);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sorter.setDelay(slider.getValue()*100);
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.setSize(400,400);
		buttons.setBackground(buttonsPanelColor);
		buttons.add(new JLabel("Speed"));
		buttons.add(slider);
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
