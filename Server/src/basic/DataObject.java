package basic;

import java.io.*;

public class DataObject implements Serializable{

	String message;
	
	public DataObject(){
	
	}

	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
}