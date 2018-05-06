/**
@RzymArab.java
@Jan Kulbiński
*/

class RzymArabException extends Exception
{
 RzymArabException(String w)
	{
	 super(w);
	}
};

class RzymArab 
{
 private static String[] Rliczby= {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
 private static int[] Aliczby= {1,4,5,9,10,40,50,90,100,400,500,900,1000};

 public static String arabtorzym(int arab) throws RzymArabException
   {
    String wynik="";  		
    if(arab<=0 || arab>3999)  
   	 throw new RzymArabException("Nieprawidłowy zakres");
   			
    for(int i=12;i>=0 && arab>0;i--)
   	 while(arab>=Aliczby[i])
   		 {
   	 	  arab=arab-Aliczby[i];
     		  wynik=wynik+Rliczby[i];	
    		 }		 
    return wynik;
   }

 public static int rzymtoarab(String wzorzec) throws RzymArabException
	{
    int dlugosc=wzorzec.length();
    int[] tab= new int[dlugosc]; // w tab beda liczby arabskie odpowiadajace literom z "wzorzec" 
    for(int i=0;i<dlugosc;i++) // uzupełnienie tab 
   	{
   	 boolean czyjest= false; // zmienna okresla czy szukana litera z 'wzorzec' zostala odnalezona w Rliczby
   	 for(int j=0; j<13;j=j+2)
   	 	{
   	 	char wz=wzorzec.charAt(i); 
   	 	char rl=Rliczby[j].charAt(0);
   	  	 if(wz==rl) 	// if(wzorzec[i]==Rliczby[j])
   	  	 	{
   	  	 	 czyjest=true;
   	  	 	 tab[i]=Aliczby[j];
   	  	 	 break;
   	  	 	}
   	  	 }
  		 if(!czyjest) throw new RzymArabException("Bledne dane");
  		}
  		
  	int suma=0;	 // szukana liczba
	for(int i=0;i<dlugosc-1;i++)     
   	{
   	 if(tab[i]<tab[i+1])
   	 	 suma=suma-tab[i];	 	 
   	 else
   		 suma=suma+tab[i];
   	}	 
   suma=suma+tab[dlugosc-1];   
   String rzymskie=arabtorzym(suma);
   if(rzymskie.equals(wzorzec)) //czy wzorzec jest poprawnie zapisany
   	return suma;
   else	
     throw new RzymArabException("Bledne dane");
  }

};

public class RzymArabLepiej
{
 public static void main(String arg[])
 {
  if(arg.length==0)
  	System.out.println("Brak danych");
  
  for(int i=0;i<arg.length;i++)
 	{ 	 
 	 System.out.print(arg[i]+" - ");
 	 try
 	 	{
 	 	 int n=Integer.parseInt(arg[i]); //wczytano liczbe
 	 	 System.out.println(RzymArab.arabtorzym(n));
 	 	} 
 	 catch(NumberFormatException ex)	//wczytano litery
 	 	{
 	 	 try
 	 	 	{
 	 	 	 System.out.println(RzymArab.rzymtoarab(arg[i]));
 	 	 	}
 	 	 catch(RzymArabException w)
 	 	 	{
 	 	 	 System.out.println(w.getMessage());	
 	 	 	}	
 	 	}
 	 catch(RzymArabException w)
 	 	{
 	 	 System.out.println(w.getMessage()); 
 	 	}
 	} 	
 } 
}
