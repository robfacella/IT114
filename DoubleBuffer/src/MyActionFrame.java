import java.awt.*;
import java.awt.event.*;

public class MyActionFrame extends Frame{

	public MyActionFrame(){

		setSize(500,500);
		setTitle("ActionListener Example");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});

		ActionPanel ap = new ActionPanel();
		add(ap);

		setVisible(true);
	}

	public class ActionPanel extends Panel implements ActionListener{
		
		Button b1, b2;
		TextField tf;
		int counter;
		
		public ActionPanel(){
	
			b1 = new Button("Click me");
			b1.addActionListener(this);
			add(b1);

			tf = new TextField("        ");
			add(tf);
			
			b2 = new Button("?????");
			b2.addActionListener(this);
			add(b2);

		}

		public void actionPerformed(ActionEvent ae){
			
			if(ae.getSource()==b1){
			
				tf.setText("Clicked " +  ++counter);
				if(counter>=10){
					setBackground(new Color(255,0,0));
				} 
				if(counter>=20){
					setBackground(new Color(0,0,255));
				}
			}else{
				setBackground(new Color(255,255,255));
				counter = 0;
				tf.setText("Clicked " +  ++counter);
			
			}
		}
	}
	public static void main(String[] args){
		MyActionFrame maf = new MyActionFrame();
	}
}
