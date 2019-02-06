/**
@Lista4.java
@Jan Kulbiński
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class ColorAction implements ActionListener
{
 private Color c;
 private MyFrame f;
 ColorAction(Color c, MyFrame f)
 	{
 	 this.c=c;
 	 this.f=f;
 	}
 public void actionPerformed(ActionEvent e)
 	{
 	 f.setBackground(c);
 	}	
}
