package basic;

import java.io.*;
import java.net.*;

public class EchoClient{
	public static void main(String[] args){
		DataObject d1 = new DataObject();
		d1.setMessage("Did you get this?");
		
		try{
			Socket s = new Socket("localhost",8189);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			
			out.writeObject(d1);
			System.out.println("Sent message: " + d1.getMessage());
			
			d1 = (DataObject)in.readObject();
			System.out.println("Recieved message: " + d1.getMessage());
			
			out.close();
			in.close();
			s.close();
		}catch(ClassNotFoundException cnfe){//specific
			System.out.println("DataObject not found.");
		}catch(IOException ioe){//catch all
			System.out.println(ioe.getMessage());
		}
	}
}