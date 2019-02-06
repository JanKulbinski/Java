/**
@Lista4.java
@Jan Kulbiński
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class MyDialog extends Dialog implements ActionListener
{ 
 TextField [] parametry;
 TextField [] wyniki;
 Label [] napisparametry;
 
 TextField zakres;
 int x; // liczba parametrow
  
MyDialog(MyFrame f,int x,TextField zakres)
	{
	 super(f, "Dialog Window", false);
	 this.zakres=zakres;
	 this.x=x;
	
    setLayout(new GridLayout(0,3));
    addWindowListener(new MyWindowAdapter2(this));
   
    parametry= new TextField[x];
    wyniki= new TextField[x];
    napisparametry = new Label[x];
    	
    int s=(int) (x/20)+1;
	 setBounds(300,200,750,480*s);
    	
    Label e1= new Label("dana:");
    Label e2= new Label("wynik:");
    Label e3= new Label("");
    add(e3);
    add(e1);
    add(e2);

    for(int i=0;i<x;i++)
   	{ 
     	 parametry[i]= new TextField(5);
     	 napisparametry[i]= new Label("Parametr " + i);
     	 wyniki[i]= new TextField(5);
     	 wyniki[i].setEditable(false);
     	 add(napisparametry[i]);
     	 add(parametry[i]);
     	 add(wyniki[i]);
     	}      	
    setResizable(false);
   
    Button b1=new Button("Uruchom"); 
    add(b1);      
    b1.addActionListener(this);   	
    setVisible(true); 
	}
	
public void actionPerformed(ActionEvent e) 
	{
	 int tab[]=new int[x];// zapamietuje indeksy tablicy parametry, w ktorych sa dane
	 String param="";
  	 for(int j=0, i=0;i<x;i++)
  		{
  		 if(!(parametry[i].getText().equals("")))
  		 	{
  		 	 tab[j]=i;
  		 	 j++;
  		 	}
  		 param=param+parametry[i].getText()+" ";  		   		
  		}
  		
	 try
  	 	{
  	    Process child = Runtime.getRuntime().exec("java T1 "+zakres.getText()+" "+param);	
  	    BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream()));      
       BufferedReader inErr = new BufferedReader(new InputStreamReader(child.getErrorStream()));
      
       String c;
       for(int i=0; (c = in.readLine())!=null; i++)
      	  wyniki[tab[i]].setText(c);
       in.close();    
   	
   	 for(int i =0; (c = inErr.readLine())!=null;i++)
   		  wyniki[tab[i]].setText(c);
      
       inErr.close();
      }
    		
   catch(IOException ep)
	 	{
    	 wyniki[0].setText(ep.getMessage());
    	}     
   catch(IllegalArgumentException ep)
      {
       wyniki[0].setText(ep.getMessage());
      }    	
	}
}
