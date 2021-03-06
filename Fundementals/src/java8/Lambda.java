package java8;
/**
 * Lambda expressions
 * Stream API and Collections
 * Java FX
 * Nashorn
 * @author bilbowm
 *
 */
public class Lambda {
	
	private static void methodReference() {
		DoSomething<String> something = s -> System.out.println(s);
		something.go("Hello method reference!");
		something = System.out::println;
		something.go("Woah there!");
	}
	public static void main(String[] args) {
		methodReference();
	}
}

interface DoSomething<T> {
	void go(T thing);
}