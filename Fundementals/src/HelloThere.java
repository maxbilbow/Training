
public class HelloThere {

	private static int age = 50;
	
	public static void main(String [] args) 
	{
		System.out.println("Hello, there!");
		
		{
			int age = 25;
		}
		
		System.out.println(age);
	}
}
