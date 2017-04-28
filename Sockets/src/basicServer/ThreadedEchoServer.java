package basicServer;

import java.io.*;
import java.net.*;

public class ThreadedEchoServer {
	int port;
	
	public ThreadedEchoServer(int port){
		try{
			ServerSocket ss = new ServerSocket(port);
			for(;;){
				Socket s = ss.accept();
				new ThreadedEchoHandler(s).start();
			}
		}catch(IOException ioe){
				System.out.println(ioe.getMessage());
		}
	}
	
	public static void main(String [] args){
		if(args.length == 1){
			new ThreadedEchoServer(Integer.parseInt(args[0]));
		}
		else{
			System.out.println("Usage >java ThreadedEchoServer [port]");
			new ThreadedEchoServer(8189);
		}
	}

}

class ThreadedEchoHandler extends Thread{
	DataObject d;
	Socket s;
	
	public ThreadedEchoHandler(Socket s)
	{
		this.s = s;
	}
	public void run(){
		
		try{
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			
			d = (DataObject)in.readObject();
			System.out.println("Received from client: " + d.getMessage());
			
			d.setMessage("Got it!");
			System.out.println("Sending reply: " + d.getMessage());
			out.writeObject(d);
			
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
//kill server when sent:

//CHAT PROGRAM:
//user list
//<name>: <message>
//shared whiteboard