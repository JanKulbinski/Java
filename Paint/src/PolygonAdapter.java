import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.Polygon;

import javax.swing.SwingUtilities;
/**
 * creates new polygon
 * @author Jan Kulbinski
 * @version 1.0
 */
public class PolygonAdapter extends MouseAdapter 
{

	private Surface s;
	
	/**  
	 * @param s panel with displayed figures
	 */	
	PolygonAdapter(Surface s)
	{
	 this.s=s;
	}
	
	/**
	 * left mouse button creates new dot
	 * right mouse button connects all dots 
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
	 if(SwingUtilities.isRightMouseButton(e))
		{
		 Polygon polygon= new Polygon(); 
	  	 
		 for(int i=0;i<s.pointlist.size();i++)
		    {
			  Point2D p=s.pointlist.get(i);
	    	  int x= (int) p.getX();
			  int y= (int) p.getY();
			  polygon.addPoint(x, y);  
			 }	
		s.polygonlist.add(polygon);
		s.pointlist.clear();
		s.repaint();
		}
		
	else
		{
	 	 float x=(float)e.getX();
	 	 float y=(float)e.getY();
		 Point2D p=new Point2D.Float(x,y);
		 s.pointlist.add(p);
		 s.repaint();
		}
	}

}
