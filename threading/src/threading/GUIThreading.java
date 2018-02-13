package threading;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIThreading {
	public static void main(String[]args){
		ThreadedFrame frame = new ThreadedFrame(); 
	}
}
class ThreadedFrame extends Frame{
	public ThreadedFrame(){
		setSize(500, 500);
		setTitle("Threaded Frame");
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		
		ThreadedPanel tp = new ThreadedPanel();
		add(tp, BorderLayout.CENTER);
		setVisible(true);
	}
}
class ThreadedPanel extends Panel implements Runnable{
	Thread thread;

	TextField tf;
	Button b;
	List left, right;
	
	public ThreadedPanel(){
		thread = new Thread(this, "Theo");
		
		setLayout(new BorderLayout());
		tf = new TextField();
		add(tf, BorderLayout.NORTH);
		b = new Button("Click me Damnit");
		add(b, BorderLayout.SOUTH);
		left = new List();
		left.add("Left List:");
		add(left, BorderLayout.WEST);
		right = new List();
		add(right, BorderLayout.EAST);		
		thread.start();
	}
	public void run() {
		while(thread != null)//so thread only runs while relevant
		{
			right.add("Hello Nurse");			
			try {
				thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}		
	}
}