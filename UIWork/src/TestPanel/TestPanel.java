package TestPanel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class TestPanel extends Frame implements ActionListener
{
	
	public TextArea ta;
	TextField tf;
	Panel p;
	Button save, clear, load;
	
	//save clear or load text area respectively
	
	public TestPanel () 
	{
		super();
		
		setLayout(new BorderLayout());
		setTitle("This is the Title of the Frame");
		tf = new TextField();
		tf.addActionListener(this);
		add(tf, BorderLayout.NORTH);
		
		this.setBackground(Color.WHITE);
		
		ta = new TextArea();
		
		add(ta, BorderLayout.CENTER);
		
		this.setSize(500, 500);
		p = new Panel();
		
		p.setLayout(new FlowLayout());
		
		save = new Button("Save");
		clear = new Button("Clear");
		load = new Button("Load");
		
		save.setActionCommand("save");
		save.addActionListener(this);

		clear.setActionCommand("clear");
		clear.addActionListener(this);
		load.addActionListener(this);
		
		p.add(save);
		p.add(clear);
		p.add(load);
		
		add(p, BorderLayout.SOUTH);
	///////////////////////////////////////////////
		//so the frame closes
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
	/////////////////////////////////
	}

	
	public void actionPerformed(ActionEvent arg) {
		if (arg.getActionCommand() == "save")
		{
			System.out.println("save");
		}
		else if (arg.equals(clear))
		{
			System.out.println("clear");			
		}
		else if (arg.equals(load))
		{
			System.out.println("load");
		}
		else
		{
			String text = tf.getText();
			tf.setText("");
			ta.append(text + "\n");
		}
	}
	
	//move text from text field to text area
	
	//text area .append
	
	public void buttonClick(ActionEvent click) {
		String b = click.getActionCommand();
		
		if (click.equals("Save"))
		{
			try {
				BufferedWriter fileOut = new BufferedWriter(new FileWriter("filename.txt"));
				fileOut.write(ta.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (click.equals("Clear"))
		{
			ta.setText("");
		}
		else if (click.equals("Load"))
		{
			
		}
		
	}	
}

