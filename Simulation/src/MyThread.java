import java.awt.Color;
import java.util.Random;

/**
 * decides based on given probability if rectangle's color will be changed and to what color
 * @version 1.0
 * @author Jan Kulbinski
 */
public class MyThread implements Runnable 
{
	private int number,w,h;//width,height
	private double p,v;// probability, velocity
	private Random rand;
	private Color[] colorlist;
	private MyPanel mypanel;
	
	/*
	 * sets given parameters
	 * @param number thread's number (from 0 to height * width)
	 * @param p probability
	 * @param v velocity
	 * @param rand Random generator
	 * @param colorlist list which remembers colors
	 * @param mypanel panel with drawn rectangles
	 * @param h height
	 * @param w width  
	 */
	MyThread(int number,double p,double v,Random rand,Color[] colorlist,MyPanel mypanel,int w, int h)
	{
		this.number=number;
		this.rand=rand;
		this.p=p;
		this.v=v;
		this.colorlist=colorlist;
		this.mypanel= mypanel;
		this.w=w;
		this.h=h;
	}
	
	/*
	 * decides if given rectangle will change its color based on given probability, calculate this color
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
		while(true)
		{
			if(rand.nextDouble()<p) // not '<=' cause nextDouble returns from 0.0 (inclusive) 
			{
				int r,g,b;
				r=rand.nextInt(255);
				g=rand.nextInt(255);
				b=rand.nextInt(255);
				Color color= new Color(r,g,b);
				synchronized(this)
				{
					colorlist[number]=color;
				
				}
				mypanel.repaint();
			}
			
			else
			{

				int right,left,down,up;
				int xL,xP;	
				int averageRed,averageGreen,averageBlue;
				
				xL=(((number/w)*w));
				xP=((((number/w)+1)*w)-1);
				
				right=(xL+((number+1-xL)%w));
				left=(xP-((xP-(number-1))%w));			
				down=(number+w)%(h*w);
				up=(number-w);
				if(up<0)
					up=up+(h*w);
				
				synchronized(this)
				{
					averageGreen=(int)((colorlist[down].getGreen()+colorlist[up].getGreen()+colorlist[right].getGreen()+colorlist[left].getGreen())/4);
					averageRed=(int)((colorlist[down].getRed()+colorlist[up].getRed()+colorlist[right].getRed()+colorlist[left].getRed())/4);
					averageBlue=(int)((colorlist[down].getBlue()+colorlist[up].getBlue()+colorlist[right].getBlue()+colorlist[left].getBlue())/4);
					colorlist[number]=new Color(averageRed,averageGreen,averageBlue);
				}
				mypanel.repaint();
				
			}
			
			try
			{
				Thread.sleep((long)(v*(rand.nextDouble()+0.5)));	
			}
			catch(Exception e) {assert false;}
	
		}
	}
}
