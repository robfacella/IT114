package TestPanel;

import java.util.ArrayList;

public class ListTester 
{
	public void dataObjectTest()
	{

		DataObject[] ds = new DataObject[5];
		
		ds[0] = new DataObject("Hello");
		ds[1] = new DataObject("Wazzup");
		ds[2] = new DataObject("Hello again");
		ds[3] = new DataObject("Hi");
		ds[4] = new DataObject("How are ya?");
		
		for (int i=0; i< ds.length; i++)
		{
			System.out.println(ds[i].getMessage());
		}
	}
	public void arrayListTest()
	{
		ArrayList <DataObject> ds = new ArrayList<DataObject>();
		ds.add( new DataObject("Howdy"));
		ds.add( new DataObject("Bubbles"));
		ds.add( new DataObject("Cats"));
		
		for(DataObject cat: ds){
			System.out.println(cat.getMessage());
		}
	}
	public static void main(String[] args)
	{
		
	}
}

class DataObject {
	String msg;
	
	public DataObject(){
		
	}
	
	public DataObject(String msg){
		setMessage(msg);
	}
	
	public void setMessage(String message)
	{
		this.msg = message;
	}
	
	public String getMessage()
	{
		return this.msg;
	}
}