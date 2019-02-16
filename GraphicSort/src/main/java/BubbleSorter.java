import java.util.concurrent.Semaphore;

public class BubbleSorter extends Sorter {

	BubbleSorter(int numberOfElements, MyJPanel graph, Semaphore semaphore) { // number of elements to sort
		super(numberOfElements,graph, semaphore);	
	}
	
	@Override
	public void run() {
		for (int i = numbers.length; i > 0; i--) {
			for (int j = 0; j < i-1; j++) {
				try {
					semaphore.acquire();
					graph.setCompared(j,j+1);
					graph.repaint();
					Thread.sleep(300);
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(numbers[j] > numbers[j+1]) {
					int x =  numbers[j];
					numbers[j] = numbers[j+1];
					numbers[j+1] = x;
				}
			}
		}
	}

}
