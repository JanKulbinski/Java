import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

/**
 * draws  rectangles and changes their colors
 * @version 1.0
 * @author Jan Kulbinski
 */
public class MyPanel extends JPanel
{
	private Color[] colorlist;
	private int h,w; // parameters- Height,Width
	
	/*
	* creates panel and sets Width Height Background Color
	* @param h height 
	* @param w width 
	*/	
	MyPanel(int h,int w)
	{
		super();
		this.h=h;
		this.w=w;
		setBackground(Color.WHITE);
	}
	
	/*
	 * sets Height
	 * @param h height 
	 */
	public void setH(int h)
	{
		this.h=h;
	}
	
	/*
	 * sets Width
	 * @param w width 
	 */
	public void setW(int w)
	{
		this.w=w;
	}
	
	/*
	 * sets colorlist
	 * @param colorlist list with colors 
	 */
	public void setColorList(Color[] colorlist)
	{
		this.colorlist=colorlist;
	}
	
	/*
	 * draws rectangles and changes their colors
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		Dimension d=getSize();
		double panelHeight=d.getHeight();
		double panelWidth=d.getWidth();
		
		double rectangleHeight= (panelHeight/h);
		double rectangleWidth= (panelWidth/w);
		for(int i=0;i<w*h;i++)
		{
			double rectangleX=(i*rectangleWidth)%panelWidth;
			double rectangleY=((i/w)*rectangleHeight);
			g2.setColor(colorlist[i]);
			g2.fill(new Rectangle2D.Double(rectangleX,rectangleY,rectangleWidth,rectangleHeight));
		}
	}
}
