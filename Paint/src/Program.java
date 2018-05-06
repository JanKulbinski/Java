import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
*displays the main window of program 
*@author Jan Kulbinski
*@version 1.0
*/

public class Program extends JFrame implements ActionListener
{
	
  private JMenuBar menb;
  private JMenu men;
  private JMenuItem info,exit;
  private JPanel buttons;
  private Surface surface;
  private JButton rectangle,elipse,polygon,save,open,edit,clear;
  private JTextArea objinfo;
  
  /**
   *creates main window program 
   */
  Program()
  {
   super("Program");	 
   setSize(840,680);
   setLayout(null);
   menb= new JMenuBar();
   men= new JMenu("Menu");  
   exit=new JMenuItem("Exit");
   info= new JMenuItem("About");
   men.add(info);
   men.add(exit);
   menb.add(men);
   setJMenuBar(menb);
 
   buttons= new JPanel();
   buttons.setLayout(new FlowLayout(FlowLayout.LEFT));
   rectangle= new JButton("Rectangle"); 
   elipse= new JButton("Elipse");
   polygon= new JButton("Polygon");
   save= new JButton("Save");
   open= new JButton("Open");
   edit= new JButton("Edit"); 
   clear= new JButton("Clear"); 
   buttons.add(rectangle);   
   buttons.add(elipse);
   buttons.add(polygon);
   buttons.add(edit);
   buttons.add(open);
   buttons.add(save);
   buttons.add(clear);
   buttons.setBounds(0, 0, 680, 50);
   add(buttons);
  
   surface= new Surface();
   surface.setBounds(5, 55,630,630);
   add(surface);
  
   rectangle.addActionListener(this);
   elipse.addActionListener(this);
   edit.addActionListener(this);
   info.addActionListener(this);
   exit.addActionListener(this);
   polygon.addActionListener(this);
   save.addActionListener(this);
   open.addActionListener(this);
   clear.addActionListener(this);
   
   objinfo=new JTextArea("MOVING OBJECT");
   objinfo.setEditable(false);
   objinfo.setBackground(Color.WHITE);
   objinfo.setBounds(640, 54,250,90);
   add(objinfo);	

   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   setLocationRelativeTo(null);  
   setResizable(false);
  }
  
 /**
  * listens all buttons
  */
  public void actionPerformed(ActionEvent e)
  {
	 RectangleAdapter ma1=new RectangleAdapter(surface);
	 ElipseAdapter ma2=new ElipseAdapter(surface);
	 EditAdapter ma3=new EditAdapter(surface,objinfo);
	 PolygonAdapter ma4= new PolygonAdapter(surface);
	 
	 for(MouseListener act : surface.getMouseListeners()) 
	    surface.removeMouseListener(act);
	 
	 for(MouseWheelListener act : surface.getMouseWheelListeners()) 
	    surface.removeMouseWheelListener(act);
	 
	 for(MouseMotionListener act : surface.getMouseMotionListeners()) 
	    surface.removeMouseMotionListener(act);
	 
	 
	 if(e.getActionCommand().equals("Rectangle"))
	 {   	   	 
	   surface.addMouseListener(ma1);
	   surface.addMouseMotionListener(ma1);
	 }
	 
	 else if(e.getActionCommand().equals("Elipse"))
	 {
	   	 
	   surface.addMouseListener(ma2);
	   surface.addMouseMotionListener(ma2); 
	 }
	 
	 else if(e.getActionCommand().equals("Edit"))
	 {   
	   surface.addMouseListener(ma3);
	   surface.addMouseMotionListener(ma3);
	   surface.addMouseWheelListener(ma3);
	 }
	 else if(e.getActionCommand().equals("About"))
	 {   
		JOptionPane.showMessageDialog(this,"Name: Program\nAuthor: Jan Kulbinski\n"
		+ "Purpose: Creating and editing geometric objects","About",JOptionPane.INFORMATION_MESSAGE);
	 }
	 
	 else if(e.getActionCommand().equals("Exit"))
	 {   
		 System.exit(0);
	 }
	 
	 else if(e.getActionCommand().equals("Polygon"))
	 {   
		 surface.addMouseListener(ma4);
	 }
	 
	 else if(e.getActionCommand().equals("Save"))
	 {   
		 Write x=new Write(this,surface);
		 x.addRecords();
		 x.closeFile();
	 }
	 
	 else if(e.getActionCommand().equals("Open"))
	 {   
		 clearSurface();		 
		 Read x=new Read(this,surface);
		 x.readfile();
		 x.closefile();
		 surface.repaint();
	 }
	 
	 else if(e.getActionCommand().equals("Clear"))
	 {   
		 clearSurface();
		 surface.repaint();
	 }
 }
  
/**
 * brushes surface
 */  
  private void clearSurface()
  {
		 surface.list.clear();
		 surface.polygonlist.clear();
		 surface.colorlist.clear();
		 surface.active=false;
  }
 /** 
  * starts program
  * @param args- void
  */
  
  public static void main(String[] args) 
	 {
	  Program p = new Program();	
	  p.setVisible(true); 
	 }	
}
