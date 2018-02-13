package Inheritence;

//can have multiple classes in a file BUT only one can be public

public class Parent 
{
	String name;
	
	public Parent()
	{
		name = "Avacado";
	}
	public Parent(String name)
	{
		setName(name);
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void greeting()
	{
		System.out.println(" Hello I am a parrent named " + this.getName() + ".");
	}
}
