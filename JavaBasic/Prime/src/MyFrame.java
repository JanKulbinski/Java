/**
@Lista4.java
@Jan Kulbiński
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyFrame extends Frame implements ActionListener
{
 TextField zakres;
 TextField liczba; // pole z liczba parametrow  
 MyDialog myDialog;
 int x; // liczba parametrow
  
 MyFrame() 
 	{
 	 super("Program");
    setBounds(300,200,540,480);
    addWindowListener(new MyWindowAdapter());
    setFont(new Font(Font.SANS_SERIF,Font.PLAIN,20));
    setLayout(new GridLayout(0,2));
 
    setMenuBar(new MyMenu(this));
   
    Label n1= new Label("Podaj zakres");
    Label n2= new Label("Podaj liczbe parametrow");
        
    Button akcja = new Button("OK");
    akcja.addActionListener(this);
    
    liczba = new TextField(5);
    zakres = new TextField(5);
	 
	 add(n1);	
    add(zakres);
    add(n2);
    add(liczba);
    add(akcja);
    setResizable(false);
  }
  
 public void actionPerformed(ActionEvent e) 
   {
    int y; // liczba -zakres
    String z,l;
    z=zakres.getText();
    l=liczba.getText();
     
    try 
    {
     x=Integer.parseInt(l); // liczba parametrow
     y=Integer.parseInt(z);
    } 
    catch(NumberFormatException ex) {return;}
	
	 if(x<=0 || y<=0)
		return;

     myDialog= new MyDialog(this,x,zakres);  
  	}
};
