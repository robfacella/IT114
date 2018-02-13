package Homework;

public class Student extends Person{
	String id;
	
	public Student(String name, String id)
	{
		super(name);
		setID(id);
	}
	
	public void setID(String id)
	{
		this.id = id;
	}
	public String getID()
	{
		return id;
	}
	
	public String toString()
	{
		return (super.toString() + ". Student ID:" + getID() + ". ");
	}
}
