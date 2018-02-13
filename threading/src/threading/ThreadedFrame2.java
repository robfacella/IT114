//Robert Facella
//IT 114 002
//4/1/16
package threading;

import java.awt.*;
import java.awt.event.*;


public class ThreadedFrame2 extends Frame{

	ThreadedPanel2 tp;

	public ThreadedFrame2(){

		setSize(700,500);
		setTitle("Threads are fun");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});

		tp = new ThreadedPanel2("Aphrodite");
		add(tp, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args){
	
		ThreadedFrame2 tf = new ThreadedFrame2();
	}
}

class ThreadedPanel2 extends Panel implements Runnable, ActionListener, ItemListener{

	List left, right;
	Thread t;
	Button br;
	Button bl;
	TextField tf;
	TextArea ta;
	Panel south = new Panel();
	int counter;

	public ThreadedPanel2(String name){
		setLayout(new BorderLayout());
		t = new Thread(this, name);
		left = new List();
		left.addItemListener(this);
		add(left, BorderLayout.WEST);
		right = new List();
		right.addItemListener(this);
		add(right, BorderLayout.EAST);
		
		south.setLayout(new BorderLayout());
		add(south, BorderLayout.SOUTH);
		br = new Button("Clear Right List");
		br.addActionListener(this);
		south.add(br, BorderLayout.EAST);
		bl = new Button("Clear Left List");
		bl.addActionListener(this);
		south.add(bl, BorderLayout.WEST);
		
		tf = new TextField();
		tf.addActionListener(this);
		add(tf, BorderLayout.NORTH);
		ta = new TextArea();
		add(ta, BorderLayout.CENTER);
			
		t.start();
		
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource() == tf){
			String temp = tf.getText();
			tf.setText("");
			if(temp.startsWith("change to")){
				String[] temparray = temp.split(" ");
				String newtemp = temparray[temparray.length-1];
				counter = 0;
				t.setName(newtemp);
				ta.append("Thread name changed to: " + newtemp + "\n");

			}else if(temp.toString().equals("clear log")){
				ta.setText("");
			}else if(temp.startsWith("get ")){
				
				String newtemp;
				String [] ar = temp.split(" ");
				newtemp = ar[1];
				for (int i = 1; i < ar.length-1; i++)
				{
					newtemp = newtemp + " " + ar[i+1];
				}
				try{
				right.remove(newtemp);
				ta.append(newtemp + " removed.\n");}
				
				catch(IllegalArgumentException e){
					ta.append(newtemp + " not found.\n");
				}
			}
			else{
				right.add(temp);
				tf.setText("");
				ta.append("Added " + temp + " to Right List\n");
			}
		}else if(ae.getSource() == br){
			right.removeAll();
			ta.append("Removed All from Right List\n");
		}
		else if(ae.getSource() == bl){
			left.removeAll();
			ta.append("Removed All from Left List\n");
		}
		
		
	}
	public void itemStateChanged(ItemEvent ie){
		if(ie.getSource()== right){
			String temp = right.getSelectedItem();
			right.remove(temp);
			left.add(temp);
			ta.append("Moved " + temp + " from Right List to Left List\n");
		}else if(ie.getSource() == left){
			String temp = left.getSelectedItem();
			left.remove(left.getSelectedIndex());
			right.add(temp);
			ta.append("Moved " + temp + " from Left List to Right List\n");
		}
	}

	public void run(){

		while(t != null){
			right.add(t.getName() + " " + counter++);

			try{
				Thread.sleep(5000);
			}catch(InterruptedException ie){}
		}

	}

}