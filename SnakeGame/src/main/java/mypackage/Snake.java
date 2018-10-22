package mypackage;

import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * creates snake and expands it
 * @author Jan Kulbiński
 */

public class Snake {
	
	ArrayList<Rectangle2D.Float> body;
	Rectangle2D.Float head;
	int length;
	
	Snake() {		
		body= new ArrayList<Rectangle2D.Float>();
		head= new Rectangle2D.Float();
		length=1;
		addHead();
	}
	
/**
 * generates snake's head in random place on game panel
 */
	private void addHead() {	
		
		Random rand=new Random();
		float x = (float)(rand.nextInt(36))*12;
		float y = (float)(rand.nextInt(36))*12;
		head.setRect(x,y,12,12);
		body.add(head);

	}
	
/**
 * expands snake's body
 * @param position -position in ArrayList body
 * @param x position of added rectangle
 * @param y position of added rectangle
 */
	public void addBody(int position,float x,float y) {
		
		body.add(position,new Rectangle2D.Float(x,y,(float)12.0,(float)12.0));
		length++;
	}
	
/**
 * shorten snake's body
 * @param position -position in ArrayList body
 */
	public void removeBody(int position) {
		
		body.remove(position);
		length--;
	}
}
