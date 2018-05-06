import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * saves currently displayed figures into file SAVE.txt
 * @author Jan Kulbinski
 * @version 1.0
 */

public class Write
{
		private Formatter file;
	 	private Surface surface;
	 	
	 	/**
	 	 * opens file which will store figures  
	 	 * @param program main window program 
	 	 * @param surface panel with displayed figures
	 	 */
		Write(Program program, Surface surface)
		{
			this.surface=surface;
			
			try
			{
				file= new Formatter("SAVED.txt");
			}
			
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(program,"Error: Could not save file","Error",JOptionPane.ERROR_MESSAGE);				
			}
		}
		
		/**
		 * puts figures data to file  
		 */		
		public void addRecords()
		{
			for(int i=0;i<surface.list.size();i++)
				{
					Shape x= surface.list.get(i);
					if(x instanceof Ellipse2D.Float)
						{
							file.format("%s ", "e");
							file.format("%f ", ((Ellipse2D.Float) x).getX());
							file.format("%f ", ((Ellipse2D.Float) x).getY());
							file.format("%f ", ((Ellipse2D.Float) x).getWidth());
							file.format("%f ", ((Ellipse2D.Float) x).getHeight());
						}
					
					else
						{
							x= surface.list.get(i);
							file.format("%s ", "r");
							file.format("%f ", ((Rectangle2D.Float) x).getX());
							file.format("%f ", ((Rectangle2D.Float) x).getY());
							file.format("%f ", ((Rectangle2D.Float) x).getWidth());
							file.format("%f ", ((Rectangle2D.Float) x).getHeight());
						}
					
					Color color= surface.colorlist.get(i);
					file.format("%d ", color.getRed());
					file.format("%d ", color.getGreen());
					file.format("%d ", color.getBlue());	
				}
			
			file.format("%s ","K");

			for(int i=0;i<surface.polygonlist.size();i++)
				{	
					Polygon x=surface.polygonlist.get(i);
					for(int j=0;j<x.npoints;j++)
						{
							file.format("%d ",x.xpoints[j]);
							file.format("%d ",x.ypoints[j]);
						}
					file.format("%s ", "K2");
				}
			
			file.format("%s ", "K3");
			
		}
		
		/**
		 * closes file
		 */
		public void closeFile()
		{
			file.close();
		}
}
