package Homework;

public class Person 
{
	String name;
	
	
	public Person()
	{
		
	}
	public Person(String name)
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
	public String toString()
	{
		return getName();
	}
}
//employeee (child of person) with tax id
//hourly employee and salary employee
//
//grad student with advisor
//undergrad with major
