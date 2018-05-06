import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
/**
 * creates new ellipse
 * @author Jan Kulbinski
 * @version 1.0
 */
public class ElipseAdapter extends MouseAdapter 
{
	private int pos=-1;
	private Point start;
   private Surface s;
   private Ellipse2D.Float elipse;
   
   /**
     * removes disconnected dots
     * deactivates figure 
     * @param s panel with displayed figures
     */
   ElipseAdapter(Surface s)
   {
  	 this.s=s; 
  	 s.active=false;
  	 s.pointlist.clear(); 
   }
   
    /**
     * sets coordinates of upper left corner
     */
	@Override
   public void mousePressed(MouseEvent event)
	{
    start = event.getPoint();
    float x=(float)start.getX();
    float y=(float)start.getY();  
    elipse= new Ellipse2D.Float(x,y,0,0);
    s.list.add(elipse);
    pos=1;
    s.colorlist.add(Color.black);
    s.repaint();
   }
	

	
   @Override
   public void mouseReleased(MouseEvent event)
   {
    pos = -1;
   }
	
   /**
	 * creates new ellipse 
	 */
   @Override
    public void mouseDragged(MouseEvent event)
    {
     if(pos == -1) 
    	 return;     
     
     Point p = event.getPoint();
     float x=(float)start.getX();
     float y=(float)start.getY();
     float x2=(float)p.getX();
     float y2=(float)p.getY();
     x2=(x2-x);
     y2=(y2-y);
     if(x2<0 || y2<0)
     	{
    	 y2=0;
    	 x2=0;
     	}
     
     Ellipse2D.Float elipse= new Ellipse2D.Float(x,y,x2,y2);
     s.list.remove(s.list.size()-1);
     s.list.add(elipse);
     s.colorlist.add(Color.black);
     s.repaint();
    } 
}
