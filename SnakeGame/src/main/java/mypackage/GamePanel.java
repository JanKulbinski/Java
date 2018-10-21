package mypackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

/**
 * displays game and listens to instructions from user
 * @author Jan Kulbiński
 */
public class GamePanel extends JPanel implements KeyListener {

	private enum Directions {
		LEFT,RIGHT,UP,DOWN,NO;
	}
	
	private Snake snake;
	private Rectangle2D.Float blueberry;
	private Directions direction; //current direction in which snake is moving
	private Random rand;
	private boolean gameOver;
	
	public GamePanel() {
		
		setSize(576,576);
		setBackground(Color.BLACK);
		addKeyListener(this);
		
		snake=new Snake();
		blueberry=new Rectangle2D.Float();
		direction=Directions.NO;	
		rand = new Random();
		gameOver=false;
		newBlueberry();	
	}
	
/**
 * displays snake and blueberry 
 */
	@Override
    protected void paintComponent(Graphics g) {
		
		Graphics2D g2d= (Graphics2D) g;
        super.paintComponent(g2d); 
        
        if(gameOver) {
        	g2d.setColor(Color.RED);
        	g2d.setFont(new Font("TimesRoman", Font.PLAIN,23));
        	g2d.drawString("GAME OVER",200,100);
        	g2d.setFont(new Font("TimesRoman", Font.PLAIN,15));
        	g2d.drawString("Your score: "+(snake.length-1),200,150);       	
        	return;
        }
        
        g2d.setColor(Color.RED);
        g2d.fill(snake.head);
              
        g2d.setColor(Color.GREEN);
        for(int i=0;i<snake.length-1;i++)
        	g2d.fill(snake.body.get(i));
    
        g2d.setColor(Color.BLUE);
        g2d.fill(blueberry);
        
	}
	
/*
 * creates new blueberry (which will be eaten by snake) in random place	
 */
	public void newBlueberry() {
		float x =(float)(rand.nextInt(36))*12;
		float y =(float)(rand.nextInt(36))*12;

		for(int i=0;i<snake.body.size();i++)
			if(snake.body.get(i).contains((double) x, (double) y)) {
				newBlueberry();
				return;
			}
		blueberry.setRect(x, y, 12, 12);
	}

/*
 * 	moves snake 
 */	
	public void running() {
		while(true) {  
				
				float x=(float)snake.head.getX();
				float y=(float)snake.head.getY();

			    if(direction==Directions.LEFT) 		// moves snake's head
			    	snake.head.setRect(x-12, y, 12, 12);
			    
			    else if(direction==Directions.RIGHT) 
			    	snake.head.setRect(x+12, y, 12, 12);
			    
			    else if(direction==Directions.UP)
			    	snake.head.setRect(x, y-12, 12, 12);

			    else if(direction==Directions.DOWN) 
					snake.head.setRect(x, y+12, 12, 12);
			    
			    if(snake.length>1) {				// moves snake's body
			    	snake.addBody(0,x, y);
			    	snake.removeBody(snake.length-2);
			    }
			    
			    if(blueberry.contains(snake.head.getCenterX(), snake.head.getCenterY())) {		// checks if snake's head has eaten a blueberry  	
		    		float lastX;
		    		float lastY;
		    		
			    	if(snake.length==1) {
			    		lastX=(float)snake.head.getX(); 
			    		lastY=(float)snake.head.getY();	
			    	}
			    	
			    	else {
			    		lastX= (float)((snake.body.get(snake.body.size()-1)).getX());
			    		lastY= (float)((snake.body.get(snake.body.size()-1)).getY());
			    	} 
			    	
			    	if(direction==Directions.LEFT) {
			    		snake.addBody(snake.body.size()-1,lastX+12, lastY);
			    	}

			    	else if(direction==Directions.RIGHT) {
			    		snake.addBody(snake.body.size()-1,lastX-12, lastY);
			    	}

			    	else if(direction==Directions.UP) {
			    		snake.addBody(snake.body.size()-1,lastX, lastY+12);
			    	}

			    	else if (direction==Directions.DOWN) {
			    		snake.addBody(snake.body.size()-1,lastX, lastY-12);
			    	}
			    	
			    	newBlueberry();
			    }
			    
			    int xHead=(int)snake.head.getCenterX();
			    int yHead=(int)snake.head.getCenterY();
			    
			    if(!contains(xHead,yHead)) {	//checks if snake's head is beyond game's panel and teleports it
			    	if(xHead<0)
			    		snake.head.setRect(480, y, 12, 12);
			    	if(yHead<0)
			    		snake.head.setRect(x, 456, 12, 12);
			    	if(xHead>456)
			    		snake.head.setRect(0, y, 12, 12);
			    	if(yHead>444)
			    		snake.head.setRect(x, 0, 12, 12);
			    }
			    
			   if(bodyEaten(xHead,yHead)) {		
				   gameOver=true;
				   repaint();
				   break;
			   }
			    	
			   repaint();
			   try {
				   TimeUnit.MILLISECONDS.sleep(100);
				} 
			   catch (InterruptedException e) {
					e.printStackTrace();	
				}
			    
			}	
		}
	
/**
 * checks if snake has eaten itself
 */
	private boolean bodyEaten(int x, int y) { 	
	
	   for(int i=0;i<snake.body.size()-1;i++)	
		   if(snake.body.get(i).contains(x,y))
			   return true;	   
	   
	   return false;
	}
	
/**
 * changes current direction
 */
	
	public void keyPressed(KeyEvent e) {
	    int key=e.getKeyCode();		
	    
	    if(key == KeyEvent.VK_LEFT && direction!=Directions.RIGHT)
	    	direction=Directions.LEFT;

	    if(key == KeyEvent.VK_RIGHT && direction!=Directions.LEFT)
	    	direction=Directions.RIGHT;

	    if(key == KeyEvent.VK_UP && direction!=Directions.DOWN)
	    	direction=Directions.UP;

	    if(key == KeyEvent.VK_DOWN && direction!=Directions.UP)
	    	direction=Directions.DOWN;

	}
	
	
	public void keyReleased(KeyEvent evt) {
	}

	
	public void keyTyped(KeyEvent e) {
	}
	
}
