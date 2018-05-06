import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;
/**
 * reads figures saved in file SAVE.txt
 * @author Jan Kulbinski
 * @version 1.0
 */

public class Read
	{
		private Scanner scan;
		private Surface surface;
		Program program;
		
		/**
		 * opens file   
		 * @param program main window program 
		 * @param surface panel with displayed figures
		 */
		
		Read(Program program,Surface surface)
		{
			this.program=program;
			this.surface=surface;
			try
				{
					scan= new Scanner(new File("SAVED.txt"));
				}
			catch(Exception e)
				{
					JOptionPane.showMessageDialog(program,"Error: could not find file","Error",JOptionPane.ERROR_MESSAGE);				
				}		
		}
	
		/**
		 * reads figures from file
		 */		
		public void readfile()
		{
			 while(!scan.hasNext("K"))
					{
					 String name,z;
					 float x,y,w,h;
					 int r,g,b;		 
					 Color color;	
					
					 name=scan.next();
					 
					 z=scan.next();
					 z=z.replaceAll(",",".");
					 x=Float.parseFloat(z);
					 
					 z=scan.next();
					 z=z.replaceAll(",",".");					 
					 y=Float.parseFloat(z);
					 
					 z=scan.next();
					 z=z.replaceAll(",",".");					 
					 w=Float.parseFloat(z);
					 
					 z=scan.next();
					 z=z.replaceAll(",",".");					 
					 h=Float.parseFloat(z);
					 					 
					 r=Integer.parseInt(scan.next());
					 g=Integer.parseInt(scan.next());
					 b=Integer.parseInt(scan.next());
					 color= new Color(r,g,b);
					 surface.colorlist.add(color);
					 
					 if(name.equals("e"))
						 surface.list.add(new Ellipse2D.Float(x, y, w, h));
					 else
						 surface.list.add(new Rectangle2D.Float(x, y, w, h));
					 }
			 scan.next();
			 while(!scan.hasNext("K3"))
				 {
					 Polygon pol=new Polygon();
					 
					 while(!scan.hasNext("K2"))
						 {
							 int x=Integer.parseInt(scan.next());;
							 int y=Integer.parseInt(scan.next());;
							 pol.addPoint(x, y);
						 }
					 scan.next();
					 surface.polygonlist.add(pol);
				 }
		}
		
		/**
		 * closes file
		 */
		
		public void closefile()
		{
			scan.close();
		}
	 
		
	}
