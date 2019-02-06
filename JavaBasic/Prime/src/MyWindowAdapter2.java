/**
@Lista4.java
@Jan Kulbiński
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyWindowAdapter2 extends WindowAdapter 
{
 MyDialog d;
 
 MyWindowAdapter2(MyDialog d)
 {
  this.d=d;
 }
 
 public void windowClosing(WindowEvent e) { d.setVisible(false); }
};
