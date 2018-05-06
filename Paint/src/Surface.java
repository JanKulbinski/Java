import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import javax.swing.JPanel;
import java.awt.BasicStroke;

/**
 * stores and displays all geometric figures
 * @author Jan Kulbinski
 * @version 1.0
 */
public class Surface extends JPanel
{	
 boolean active=false; // shows if any figure is active
 Shape rs;	// active figure
 List<RectangularShape> list = new ArrayList<RectangularShape>();  
 List<Color> colorlist= new ArrayList<Color>(); 
 List<Polygon> polygonlist= new ArrayList<Polygon> (); 
 List<Point2D> pointlist= new ArrayList<Point2D>();  
 
 /**
  * sets colors of panel with figures 
  */
 
 Surface()
  {
   setBackground(Color.WHITE);
  }
 
/**
 * paint figures 
 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
 */
 
  public void paintComponent(Graphics g)
  {
	  
   super.paintComponent(g);
   Graphics2D g2 = (Graphics2D) g;   
      
   for(int i=0;i<list.size();i++)
   	{
   		g2.setColor(colorlist.get(i));
   		g2.fill(list.get(i));  
   	}
      
      
   for(int i=0;i<polygonlist.size();i++)
   	{ 
   		g2.setColor(Color.BLACK);
   		g2.draw(polygonlist.get(i));  
   	}
      
   for(int i=0;i<pointlist.size();i++)
   	{
  	  	  Point2D p=pointlist.get(i);
    	  int x= (int) p.getX();
    	  int y= (int) p.getY();
  	  	  g2.fillOval(x, y, 5, 5); 
    	}
      
      if(active)
       {
    	   Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    	   g2.setStroke(dashed);
    	   g2.setColor(Color.red);
    	   g2.draw(rs);
       }    

  }
 
}

