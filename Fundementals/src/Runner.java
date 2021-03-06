
public class Runner implements Runnable {

	private final String name;

	public Runner(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		for (int i = 0; i<100; ++i)
			System.out.println("Runner " + name + ": " + i);
		sayHello(name);
	}

	public static synchronized void sayHello(String name) {
		System.out.println("Hello, " + name);
	}
}