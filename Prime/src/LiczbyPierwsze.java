/**
@LiczbyPierwsze.java
@Jan Kulbiński
*/
import java.util.ArrayList;
import java.util.List;

public class LiczbyPierwsze
{
 List<Integer> prime = new ArrayList<Integer>();
 int vsize;	//rozmiar wektora  
 
 public LiczbyPierwsze(int n)
 	{
	 int[] tab= new int[n+1];
	 for(int i=2;i<=n;i++) //wypełnianie sita eratostenesa true
		 tab[i]=1;
	
	 double s=Math.sqrt(n);	
	 for(int i=2;i<=s;i++) // sito eratostenesa
	  	if(tab[i]==1)
			for(int j=i*i;j<=n;j=j+i)
				tab[j]=0;
		
	 for(int i=2;i<=n;i++) //przepisanie liczb pierwszych z sita do wektora
		 if(tab[i]==1)
			 prime.add(i);
			
	vsize=prime.size();	
 	}
	
	
 public int liczba(int m) //metoda zwraca m ta liczbe z wektora
	{
    if(m>=0 && m<vsize)
 		{
 		 int x=(int)prime.get(m);
 		 return x;
		}	 
	 else
		return -1;
	}
}
