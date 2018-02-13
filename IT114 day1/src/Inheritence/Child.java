package Inheritence;

public class Child extends Parent
{

	public Child(){
		super();
	}
	
	public void greeting()
	{
		System.out.println(" Hello I am a child named " + this.getName() + ".");
	}
}
