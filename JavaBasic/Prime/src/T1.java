/**
@Test.java
@Jan Kulbiński
*/

public class T1
{
 public static void main(String args[])
 {
  if(args.length<2)
 	{
	 System.out.println("zla liczba argumentow");
	 System.exit(0);
	}
	
	int n=0;
	try
	{
	 n=Integer.parseInt(args[0]);	
	}
	
	catch(NumberFormatException ex)
	{
	 System.out.println("Nieprawidłowy zakres");
	 System.exit(0);
	}	
	
	if(n<2)
	{
	 System.out.println("Nieprawidłowy zakres");
	 System.exit(0);
	}
	
	LiczbyPierwsze a= new LiczbyPierwsze(n);
	
	for(int i=1;i<args.length;i++)
	{
	 int x=0;
	 try
		{
	  	 x=Integer.parseInt(args[i]);
		 x=a.liczba(x);
		}
		
	 catch(NumberFormatException ex)
		{
	  	 System.out.println("nieprawidlowa dana");
	  	 continue;
		}
		
	if(x<0)
		 System.out.println("liczba spoza zakresu");
	else
		System.out.println(x);
	}
 }
}
