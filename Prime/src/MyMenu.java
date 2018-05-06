/**
@Lista4.java
@Jan Kulbiński
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyMenu extends MenuBar implements ActionListener
{
 private Menu menu1, submenu1,submenu2;
 private MenuItem i1, i2, i3, i4, i5,i6, exit;
 MyFrame f;
 MyMenu(MyFrame f)
 	{
 	 this.f=f;
 	 menu1= new Menu("Ustawienia");
 	 add(menu1);	 
 	 submenu1= new Menu("Zmien kolor");
 	 submenu2= new Menu("Zmien czcionke");
 	 menu1.add(submenu1);
 	 menu1.add(submenu2); 	 
 	 exit= new MenuItem("Zakoncz");
 	 exit.addActionListener(this);
 	 menu1.add(exit);
	 
	 i1=new MenuItem("Czerwony");
	 i2=new MenuItem("Zolty");
	 i3=new MenuItem("Niebieski");
	 i4=new MenuItem("TimesRoman");
	 i5=new MenuItem("Helvetica");
	 i6=new MenuItem("Courier"); 
	 submenu1.add(i1);
	 submenu1.add(i2);
	 submenu1.add(i3);
	 submenu2.add(i4);
	 submenu2.add(i5);
	 submenu2.add(i6);
	 
	 ColorAction yAction= new ColorAction(Color.YELLOW,f);
	 ColorAction rAction= new ColorAction(Color.RED,f);
	 ColorAction bAction= new ColorAction(Color.BLUE,f);
 	 
 	 i2.addActionListener(yAction);
 	 i1.addActionListener(rAction);
 	 i3.addActionListener(bAction);
 	 i4.addActionListener(this);
 	 i5.addActionListener(this);
 	 i6.addActionListener(this);
 	 } 
 
 public void actionPerformed(ActionEvent e)
 	{
 	 if(e.getActionCommand().equals("Zakoncz"))
 	 	System.exit(0);
 	 else if(e.getActionCommand().equals("TimesRoman"))
 	 	f.setFont(new Font("TimesRoman", Font.PLAIN, 18));
 	 else if(e.getActionCommand().equals("Helvetica"))
 	 	f.setFont(new Font("Helvetica", Font.PLAIN, 18));
 	 else if(e.getActionCommand().equals("Courier"))
 	 	f.setFont(new Font("Courier", Font.PLAIN, 18));
 	} 	
}
