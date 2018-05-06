import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
/**
 * creates main window, get parameters and starts new threads   
 * @version 1.0
 * @author Jan Kulbinski
 */
public class Myframe extends JFrame implements ActionListener
{
	private JPanel buttons;
	private MyPanel rectangles;
	private JButton run;
	private JLabel probabilityLabel,velocityLabel,heightLabel,widthLabel;
	private TextField dataP,dataV,dataH,dataW;
	private Color c;
	private Color[] colorlist;
	private Random rand;
	private Thread [] threads;
	private int height,width; 
	private double probability,velocity; 
	
	/**
	 * creates main window
	 */	
	Myframe()
	{
		super("Random colors");
		setBounds(450,250,400,220);
		setLayout(new BorderLayout());

		height=0;
		width=0;
		
		rectangles=new MyPanel(height,width);
		buttons= new JPanel();
		buttons.setBackground(new Color(194,207,104));
		

		probabilityLabel=new JLabel("Probability      ");
		velocityLabel=new JLabel("Velocity");
		heightLabel=new JLabel("Height");
		widthLabel=new JLabel("Width");
		
		c=new Color(238,248,166);
		dataP=new TextField(6);
		dataV=new TextField(6);
		dataH=new TextField(6);
		dataW=new TextField(6);
		dataP.setBackground(c);
		dataV.setBackground(c);
		dataH.setBackground(c);
		dataW.setBackground(c);
						
		run=new JButton("Run!");
		run.setBackground(new Color(150,166,55));
		run.addActionListener(this);
		
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));		
		buttons.add(probabilityLabel);
		buttons.add(dataP);
		buttons.add(velocityLabel);
		buttons.add(dataV);
		buttons.add(heightLabel);
		buttons.add(dataH);
		buttons.add(widthLabel);
		buttons.add(dataW);				
		buttons.add(run);		
		
		add(rectangles,BorderLayout.CENTER);
		add(buttons,BorderLayout.EAST);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);		
	}
	
	/*
	 * get parameters and starts threads if 'Run' Button clicked
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String hs,ws,vs,ps;
		
		try
		{
			hs=dataH.getText();			
			height=Integer.parseInt(hs);
			if(height<=0)
			{
				dataH.setText("Invalid parameter!");
				return;
			}
		}		
		catch(NumberFormatException ex)
		{			
			dataH.setText("Invalid parameter!");
			return;
		}

		
		try
		{
			ws=dataW.getText();
			width=Integer.parseInt(ws);
			if(width<=0)
			{
				dataW.setText("Invalid parameter!");
				return;
			}			
		}
		catch(NumberFormatException ex)
		{
			dataW.setText("Invalid parameter!");
			return;
		}
		
		
		try 
		{
			vs=dataV.getText();
			velocity=Double.parseDouble(vs);
			if(velocity<=0)
			{
				dataV.setText("Invalid parameter!");
				return;
			}
		}
		catch(NumberFormatException ex)
		{
			dataV.setText("Invalid parameter!");
			return;
		}
		
		
		try
		{
			ps=dataP.getText();
			probability=Double.parseDouble(ps);
			if(probability<0 || probability>1)
			{
				dataP.setText("Invalid parameter!");
				return;
			}
		}
		catch(NumberFormatException ex)
		{
			dataP.setText("Invalid parameter!");
			return;
		}
		
		
		colorlist= new Color[height*width];
		rand= new Random();
		
		for(int i=0;i<height*width;i++)
		{
			int r,g,b;
			r=rand.nextInt(255);
			g=rand.nextInt(255);
			b=rand.nextInt(255);
			Color color= new Color(r,g,b);
			colorlist[i]=color;			
		}
		
		rectangles.setColorList(colorlist);
		rectangles.setH(height);
		rectangles.setW(width);
		repaint();
		
		threads= new Thread[height*width];
		for(int i=0;i<height*width;i++) 
		{
			threads[i]= new Thread(new MyThread(i,probability,velocity,rand,colorlist,rectangles,width,height));
			threads[i].start();
		}


	}
	
	/*
	 * starts main constructor 
	 */		
	public static void main(String[] args) 
	{
		Myframe frame= new Myframe();
		frame.setVisible(true);
		
	}

}
