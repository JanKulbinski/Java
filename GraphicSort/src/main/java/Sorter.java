import java.util.Random;
import java.util.concurrent.Semaphore;

public abstract class Sorter implements Runnable {
	
	protected int[] numbers;
	protected MyJPanel graph;
	protected Semaphore semaphore;
	protected int delay;
	
	Sorter(int numberOfElements, MyJPanel graph, Semaphore semaphore) { // number of elements to sort
		
		Random rand = new Random();
		
		numbers = new int[numberOfElements];
		for (int i = 0; i < numberOfElements; i++) {
			numbers[i] =  rand.nextInt(100);
		}		
		
		this.graph = graph;
		this.semaphore = semaphore;
		delay = 300;
	}
	
	public int[] getNumbers() {
		return numbers; 
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public abstract  void run();
}
