import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import javax.swing.JColorChooser;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
/**
 * provides editing functions such as: changing colors, activating, moving and scaling figures 
 * @version 1.0
 * @author Jan Kulbinski
 */
public class EditAdapter extends MouseAdapter implements MouseWheelListener
{
    private Surface s;
    private boolean move=false;
    private int x;
    private int y;
    private int movedfigureindex=-1;
    private RectangularShape movedfig; 
    private JTextArea objinfo;
  
   /**
   * remove disconnected points    
   * @param s panel which displays figures
   * @param objinfo shows informations about moving figure
   */
    EditAdapter(Surface s,  JTextArea objinfo)
    {
     this.s=s;
  	  this.objinfo=objinfo;
  	  s.pointlist.clear();
    }
    
    /**
     *activates figure and shows ColorChooser 
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
    	if(SwingUtilities.isRightMouseButton(e) && s.active)
    	{
    		Color color=JColorChooser.showDialog(null, "Choose object color", Color.black);
    		int i=s.list.indexOf(s.rs);
    		s.colorlist.set(i, color);
            s.active=false;  
            s.repaint();
    		return;
    	}
    		
    	if(e.getClickCount()==2)
    	{
    		for(int i=0; i<s.list.size();i++)    	
            {
    			RectangularShape fig= s.list.get(i);	
            	
            	if(fig.contains(e.getX(),e.getY()))
            	{       	
            	 s.rs=fig;		
            	 s.active=true;
            	 s.repaint();
            	 return;
            	}        
            }	
    		     	
          s.active=false;  
          s.repaint();
    	}
   }
    
    /**
     * checks if clicked point belongs to any figure
     * if so figure can be moved
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
     x = e.getX();
     y = e.getY();
     
     if(s.active) 
    	 {
    	  move=false;
    	  return;
    	 }
     
     for(int i=0; i<s.list.size();i++)
        {  	  
    	   movedfig= s.list.get(i);
        	
        	if(movedfig.contains(x,y))
        		{    	
        		 movedfigureindex=-1;	
        		 move=true;
        		 return;
        		}        
        }

     for(int i=0; i<s.polygonlist.size();i++)
   	  { 
   		Polygon polygon= s.polygonlist.get(i);
     	
   		if(polygon.contains(x,y))
   		  {       		
   		   move=true;
   		   movedfigureindex=i;		 
   		   return;
   		  }	        
   	  }
     
     movedfigureindex=-1;
     move=false;
    }  
    
    /**
     *moves held figure
     */
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
    	if(!move)
    		return;

        int dx = (e.getX()-x);
        int dy = (e.getY()-y);
        x=x+dx;
        y=y+dy;
        
        if(movedfigureindex!=-1)
        {
        	objinfo.setText("MOVING OBJECT");
        	objinfo.append("\ncoordinates of the object:\n"+"("+x+","+y+")\n");
        	Polygon polygon= s.polygonlist.get(movedfigureindex);
        	polygon.translate(dx,dy);
        	s.polygonlist.set(movedfigureindex, polygon);
        	s.repaint();
        	return;
        }
        
        int i= s.list.indexOf(movedfig);
        
        float fx=(float) movedfig.getX();
        float fy=(float) movedfig.getY();
        float h= (float) movedfig.getHeight();
        float w= (float) movedfig.getWidth();
        fx=fx+(float)dx;
        fy=fy+(float)dy;

        
        if(movedfig instanceof Ellipse2D.Float)
        {
        	objinfo.setText("MOVING OBJECT");
        	movedfig= new Ellipse2D.Float(fx, fy, w, h);
        	objinfo.append("\ncoordinates of the object:\n"+"("+fx+","+fy+")\n"+"height="+h
        			+ "\nwidth="+ w);
        }
        
        if(movedfig instanceof Rectangle2D.Float)
        {
        	objinfo.setText("MOVING OBJECT");
        	movedfig= new Rectangle2D.Float(fx, fy, w, h);
        	objinfo.append("\ncoordinates of the left corner:\n"+"("+fx+","+fy+")\n"+"height="+h
        			+ "\nwidth="+ w);
        }
        s.list.set(i,movedfig);
        s.repaint();
    }
    
   /**
    * scales figure indicated by cursor
    */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) 
    {      
    	if(s.active) return;
    		
        int x = e.getX();
        int y = e.getY();

        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) 
        {

            for(int i=0; i<s.list.size();i++)
            {
            	RectangularShape fig= s.list.get(i);
            	
            	if(fig.contains(x,y))
            	{
            		float amount =  e.getWheelRotation() * 5f;
            		float fx= (float) fig.getX();
            		float fy= (float) fig.getY();
            		float h= (float) fig.getHeight();
            		float w= (float) fig.getWidth();
            		
            		if(fig instanceof Ellipse2D.Float)
            		{
            			
            			Ellipse2D.Float elip= new Ellipse2D.Float(fx,fy,w+amount,h+amount);
            			s.list.set(i, elip);
            			
            			
            		}
            		
            		if(fig instanceof Rectangle2D.Float)
            		{
            			Rectangle2D.Float rect= new Rectangle2D.Float(fx,fy,w+amount,h+amount);
            			s.list.set(i, rect);
            		}
            		
            	 s.repaint();
            	}

            }

            for(int i=0; i<s.polygonlist.size();i++)
            {
            	Polygon fig= s.polygonlist.get(i);
            	
            	if(fig.contains(x,y))
            	{            		
            		double amount = e.getPreciseWheelRotation()/25;
            		double centroidx=0;
            		double centroidy=0;
            		
            		if(amount<0)
            			{
            				amount=amount*(-1);
            				amount++;
            				amount=1/amount;
            			}
            		else 
            			amount++;
            		
            		for(int j=0;j<fig.npoints;j++)
            			{
            				centroidx=centroidx+fig.xpoints[j];
            				centroidy=centroidy+fig.ypoints[j];
            			}
            		
            		
            		centroidx=centroidx/fig.npoints;
            		centroidy=centroidy/fig.npoints;

            		for(int j=0;j<fig.npoints;j++)
            			{
            				double x3=(double)fig.xpoints[j];
            				double y3=(double)fig.ypoints[j];
            				
            				x3=x3-centroidx;
            				y3=y3-centroidy;
            				x3=x3*amount;
            				y3=y3*amount;
            				x3=x3+centroidx;
            				y3=y3+centroidy; 
            				fig.xpoints[j]=(int)x3;
            				fig.ypoints[j]=(int)y3;
            			}
            		s.polygonlist.set(i, fig);
            		s.repaint();
            		return;
            	}
            }	             
        }
    }
}
