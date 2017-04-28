package working;

import java.io.*;
import java.net.*;
import java.util.*;

import chat.ChatMessage;

public class ChatServer
{  public static void main(String[] args ) 
   {  
      ArrayList<ChatHandler> AllHandlers = new ArrayList<ChatHandler>();
		
      try 
      {  ServerSocket s = new ServerSocket(4000);
         
         for (;;)
         {  Socket incoming = s.accept( );
            new ChatHandler(incoming, AllHandlers).start();
         }   
      }
      catch (Exception e) 
      {  System.out.println(e);
      } 
   } 
}

class ChatHandler extends Thread
{  
	String name;
	public ChatHandler(Socket i, ArrayList<ChatHandler> h) 
   { 
		
   		incoming = i;
		handlers = h;
		handlers.add(this);
		try{
			in = new ObjectInputStream(incoming.getInputStream());
			out = new ObjectOutputStream(incoming.getOutputStream());
		}catch(IOException ioe){
				System.out.println("Could not create streams.");
		}
   	}
	public synchronized void broadcast(){
	
		ChatHandler left = null;
		for(ChatHandler handler : handlers){
			ChatMessage cm = new ChatMessage();
			cm.setMessage(myObject.getMessage());
			try{
				handler.out.writeObject(cm);
				System.out.println("Writing to handler outputstream: " + cm.getMessage());
			}catch(IOException ioe){
				//one of the other handlers hung up
				left = handler; // remove that handler from the arraylist
			}
		}
		handlers.remove(left);
		
		if(myObject.getMessage().equals("bye")){ // my client wants to leave
			done = true;	
			handlers.remove(this);
			System.out.println("Removed handler. Number of handlers: " + handlers.size());
		}
		System.out.println("Number of handlers: " + handlers.size());
   }

	public synchronized void listUpdate(){
		String names = "";
		for(ChatHandler handler : handlers){
			names = names + " " + handler.name;
		}
		myObject.setMessage("lIsTuPdAtEs" + names);
		broadcast();
		
	}
	
	public void privateMessage()
	{
		ChatHandler left = null;
		String [] temp = myObject.getMessage().split("::");
		String [] handlerName = temp[0].split(" ");
		String dear = ""; //TO
		for (int i = 2; i < handlerName.length; i++)
		{	
			dear = dear + handlerName[i];		
		}
		handlerName = temp[1].split(" ");
		String from = ""; //From
		for (int i = 0; i < handlerName.length; i++)
		{	
			from = from + handlerName[i];		
		}
		String text = "From " + from + ": " + temp[2];
		
		ChatHandler sendTo = null;
		for (int i = 0; i < handlers.size(); i++)
		{
			if (handlers.get(i).name.equals(dear))
			{
				sendTo = handlers.get(i);
			}
		}
		
		
		if (sendTo != null)
		{
			ChatMessage cm = new ChatMessage();
			cm.setMessage(text);
			try{
				sendTo.out.writeObject(cm);
				System.out.println("Writing to handler outputstream: " + cm.getMessage());
			}catch(IOException ioe){
				//one of the other handlers hung up
				left = sendTo; // remove that handler from the arraylist
			}
			handlers.remove(left);
		}
		System.out.println("Number of handlers: " + handlers.size());
	}
	
	
   public void run()
   {  
		try{ 	
			while(!done){
				myObject = (ChatMessage)in.readObject();
				System.out.println("Message read: " + myObject.getMessage());
				
				if (myObject.getMessage().endsWith(" joined the room."))
				{
					String []temp = myObject.getMessage().split(" ");
					String kat = "";
					for(int i = 0; i < temp.length - 3; i++)
					{
						kat = kat + temp[i];
					}
					this.name = kat;
				}
				
				else if(myObject.getMessage().endsWith(" has disconnected."))
				{
					String []temp = myObject.getMessage().split(" ");
					String kat = null;
					for(int i = 0; i < temp.length - 2; i++)
					{
						kat = kat + temp[i];
					}
					this.name = kat;
				}
				if(myObject.getMessage().startsWith("pm for "))
				{
					privateMessage();
				}
				else	
					broadcast();
				
				listUpdate();
			}			    
		} catch (IOException e){  
			if(e.getMessage().equals("Connection reset")){
				System.out.println("A client terminated its connection.");
			}else{
				System.out.println("Problem receiving: " + e.getMessage());
			}
		}catch(ClassNotFoundException cnfe){
			System.out.println(cnfe.getMessage());
		}finally{
			handlers.remove(this);
		}
   }
   
   ChatMessage myObject = null;
   private Socket incoming;

   boolean done = false;
   ArrayList<ChatHandler> handlers;

   ObjectOutputStream out;
   ObjectInputStream in;
}
