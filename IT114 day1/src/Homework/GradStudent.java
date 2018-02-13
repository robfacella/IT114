package Homework;

public class GradStudent extends Student
{
	String advisor;
	
	public GradStudent(String name, String id)
	{
		super(name, id);
		
	}
	
	public void setAdvisor(String advisor)
	{
		this.advisor = advisor;
	}
	public String getAdvisor()
	{
		return advisor;
	}
	
	public String toString()
	{
		return (super.toString() + ". Advisor:" + getAdvisor() + ". ");
	}
}
