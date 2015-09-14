
public enum AnEnum {
	Hello("hello"), Goodbye("goodbye");
	private final String name;
	private AnEnum(String name)
	{
		this.name = name;
	}
	
	public String toString() 
	{
		return name;
	}
}
