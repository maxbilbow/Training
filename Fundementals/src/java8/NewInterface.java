package java8;


public interface NewInterface {
	default void SayHello() {
		System.out.println("Hello, default method!");
	}
	
	static void JustSayHello(){
		NewInterface i = new NewInterface(){};
		i.SayHello();
	}
}
