/**
@Lista4.java
@Jan Kulbiński
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyWindowAdapter extends WindowAdapter 
{
  public void windowClosing(WindowEvent e) { System.exit(0); }
};
