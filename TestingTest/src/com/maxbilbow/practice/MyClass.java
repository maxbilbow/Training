package com.maxbilbow.practice;
public class MyClass {
	private String name;
	private int value;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)  {
		if (name == null)
			throw new NullPointerException("Name was NULL. Bad.");
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	
	public void countTo(long max) {
		for (long i=0; i < max ; ++i);
			
	}
	
	/**
	 * @param value the value to set
	 * There is a 1:1000 chance that this will not set
	 */
	public void setValue(int value) {
//		if (value < 0)
//			value *= -1;
		if(1 != (int) (Math.random() * 1000))
			this.value = value;
	}
	
	public boolean equals(Object o) {
		if (o.getClass() == this.getClass()) {
			MyClass other = this.getClass().cast(o);
			return this.name == other.name && this.value == other.value;
		}
		return false;
		
	}
	
	
}
