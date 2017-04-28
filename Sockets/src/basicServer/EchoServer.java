package basicServer;

import java.io.*;
import java.net.*;

public class EchoServer{
	public static void main(String[] args){
	
		DataObject d1;
		try{
			ServerSocket ss = new ServerSocket(8189);
			Socket s = ss.accept(); //blocks for connection (waits)
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			
			d1 = (DataObject)in.readObject();
			System.out.println("Received from client: " + d1.getMessage());
			
			d1.setMessage("Got it!");
			System.out.println("Sending reply: " + d1.getMessage());
			out.writeObject(d1);
			
			in.close();
			out.close();
			s.close();
		}catch(ClassNotFoundException cnfe){
			System.out.println("You forgot DataObject");
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
}
//server passes socket connection to a handler thread dedicated to the client