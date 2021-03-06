import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threader extends Thread {

	
	
	public static void main(String[] args) {
//		Threader t = new Threader();
//		t.start();
//		new Threader().start();
//		new Threader().start();
//	    new Thread(new Runner("  ONE")).start();
//		
//		for (int i = 0; i<100; ++i)
//			System.out.println("Main: " + i);
		
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new Runner("  TWO"));
		executor.submit(new Runner("THREE"));
		executor.submit(new Runner(" FOUR"));
		executor.submit(new Runner(" FIVE"));
		executor.submit(new Runner("  SIX"));
		executor.submit(new Runner("SEVEN"));
		executor.submit(new Runner("EIGHT"));
	}

	@Override
	public void run() {
		
		for (int i = 0; i < 100; ++i) {
			System.out.println("Threader: " + i);
		}
		super.run();
	}
	

}
