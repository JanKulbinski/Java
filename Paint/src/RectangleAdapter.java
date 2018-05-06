import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
/**
 * creates new rectangle
 * @version 1.0
 * @author Jan Kulbinski
 */

public class RectangleAdapter extends MouseAdapter
 {
 	 private int pos=-1;
 	 private Point start;
    private Surface s;
    
    /**
     * removes disconnected dots
     * deactivates figure 
     * @param s panel with displayed figures
     */
   RectangleAdapter(Surface s)
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
    float x=(float)start.getX()-5;
    float y=(float)start.getY()+5;
    start.setLocation(x, y);
        
    Rectangle2D rectangle= new Rectangle2D.Float(x,y,0,0);
    s.list.add(rectangle);
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
  	 * creates new rectangle 
  	 */
     @Override
     public void mouseDragged(MouseEvent event)
     {
      if (pos == -1) 
           return;     
      
      Point p = event.getPoint();
     
      Rectangle2D rec= new Rectangle2D.Float();
      rec.setFrameFromDiagonal(start, p);
      s.list.remove(s.list.size()-1);
      s.list.add(rec);
      s.colorlist.add(Color.black);
      s.repaint();
     }
 }