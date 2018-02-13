import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Frame{
	ChatMessage myObject;
	Socket socketToServer;
	public ObjectOutputStream myOutputStream;
	ObjectInputStream myInputStream;
	Boolean receivingdone = false;
	boolean sendingdone = false;
	GoodPanel pan;
	
	public Client(){

		setSize(1000,500);
		setLocationRelativeTo(null);
		setTitle("Bill and Ted's Excellent ChatFrame");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				try{
				pan.disconnect();
				}
				catch(NullPointerException np)
				{
					System.out.println("No Problem");
				}
				System.exit(0);
			}
		});
		pan = new GoodPanel();
		add(pan);
		setVisible(true);
	
	}
	public static void main(String [] args)
	{
		Client c = new Client();
	}
}
////////////////////////////////////////////////////////////////////////////////
class GoodPanel extends Panel implements Runnable, ActionListener,  ItemListener{
	
	Panel chatPanel;
	DrawPanel dPanel;
	Panel buttonPanel;
	List list;
	TextArea ta;
	TextField tf;
	Button connect;
	Button disconnect;
	
	String username;
	
	Socket socketToServer;
	public ObjectOutputStream myOutputStream;
	ObjectInputStream myInputStream;
	Boolean receivingdone = false;
	boolean sendingdone = false;
	
	
	public GoodPanel()   
	{
	
		setLayout(new BorderLayout());
		
		list = new List();
		list.addItemListener(this);
		//list.addItemListener(this);
		list.add("Bill");
		list.add("Ted");
		add(list, BorderLayout.WEST);
		
		chatPanel = new Panel();
		chatPanel.setLayout(new BorderLayout());
		tf = new TextField();
		tf.addActionListener(this);
		ta = new TextArea();
		chatPanel.add(tf, BorderLayout.NORTH);
		chatPanel.add(ta, BorderLayout.CENTER);
		add(chatPanel, BorderLayout.CENTER);
		
		dPanel = new DrawPanel();
		add(dPanel, BorderLayout.EAST);
		
		buttonPanel = new Panel();
		connect = new Button("Connect");
		disconnect = new Button("Disconnect");
		disconnect.setEnabled(false);
		buttonPanel.add(connect);
		connect.addActionListener(this);
		disconnect.addActionListener(this);
		buttonPanel.add(disconnect);
		add(buttonPanel, BorderLayout.SOUTH);
	
	}
	
	
	public void disconnect() {
		ChatMessage text = new ChatMessage();
		text.setMessage(username + " has disconnected.");
		try{
			myOutputStream.reset();
			myOutputStream.writeObject(text);
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}	
		text.setMessage("bye");
		try{
			myOutputStream.reset();
			myOutputStream.writeObject(text);
			disconnect.setEnabled(false);
			connect.setEnabled(true);
			list.removeAll();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}	
	}
	public void connect()
	{
		try{						

			username = tf.getText();
			tf.setText("");
			
			
			socketToServer = new Socket("127.0.0.1", 4000);

			myOutputStream =
				new ObjectOutputStream(socketToServer.getOutputStream());

			myInputStream =
				new ObjectInputStream(socketToServer.getInputStream());
			
			System.out.println("Client Connected");
			disconnect.setEnabled(true);
			connect.setEnabled(false);
			
			
			
			Thread cat = new Thread(this);
			cat.start();
			
			ChatMessage text = new ChatMessage();
			text.setMessage(username + " joined the room.");
			try{
				myOutputStream.reset();
				myOutputStream.writeObject(text);
			}catch(IOException ioe){
				System.out.println(ioe.getMessage());
			}	
		}
		catch(Exception e){
			System.out.println(e.getMessage());	
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == tf){
			ChatMessage text = new ChatMessage();
			text.setMessage(username + ": " + tf.getText());
			tf.setText("");
			try{
				myOutputStream.reset();
				myOutputStream.writeObject(text);
			}catch(IOException ioe){
				System.out.println(ioe.getMessage());
			}
		}
		else if (ae.getSource() == connect)
		{
			connect();
		}
		else if (ae.getSource() == disconnect)
		{
			disconnect();
		}

	}





	@Override
	public void run() {
		System.out.println("Listening for messages from server . . . ");
		try{
			while(!receivingdone){
				ChatMessage myObject = (ChatMessage)myInputStream.readObject();
               	if(myObject.getMessage().startsWith("lIsTuPdAtEs")){
               		list.removeAll();
               		String [] temp = myObject.getMessage().split(" ");
               		for (int i = 1; i < temp.length; i++)
               		{
               			list.add(temp[i]);
               		}
               	}
               	else
               		ta.append(myObject.getMessage() + "\n");

			}
		}catch(IOException ioe){
			System.out.println("IOE: " + ioe.getMessage());
		}catch(ClassNotFoundException cnf){
			System.out.println(cnf.getMessage());
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		
		String pm = list.getSelectedItem();
		
		ChatMessage text = new ChatMessage();
		
		try{
			ta.append("To " + list.getSelectedItem() + ": " + tf.getText() + "\n");
			text.setMessage("pm for " + pm + "::" + username + ":: " + tf.getText());
			tf.setText("");
			myOutputStream.reset();
			myOutputStream.writeObject(text);
			
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
}

class DrawPanel extends Panel{
	
	Dimension minimum;
	Dimension preferred;
	Board drawPanel;
	//Panel buttonPanel;
	
		public DrawPanel(){
			setLayout(new BorderLayout());
			minimum = new Dimension(200,200);
			preferred = new Dimension(500,500);
			
			drawPanel = new Board();
			
			add(drawPanel, BorderLayout.CENTER);
			
		}
		public Dimension getMinimumSize(){
			return minimum;
		}
		public Dimension getPreferredSize(){
			return preferred;
		}
		
}
class Board extends DoubleBuffer implements MouseListener, MouseMotionListener, ActionListener{

	int lastX=0, lastY=0;
	ArrayList<Line>lines;
   Color drawColor;
   Button red, green, blue, black, erase;
   DoubleBuffer p;
   
	public Board() {
      setLayout(new BorderLayout());
		lines = new ArrayList<Line>();
		setBackground(Color.white);
		drawColor = Color.black;
		setForeground(drawColor);
		addMouseListener(this);
		addMouseMotionListener(this);
      red = new Button("Red");
      red.addActionListener(this);
      green = new Button("Green");
      green.addActionListener(this);
      blue = new Button("Blue");
      blue.addActionListener(this);
      black = new Button("Black");
      black.addActionListener(this);
      erase = new Button("Erase");
      erase.addActionListener(this);
      p = new DoubleBuffer();
      p.add(red);
      p.add(green);
      p.add(blue);
      p.add(black);
      p.add(erase);
      add(p, BorderLayout.SOUTH);       
	}
   
   public void actionPerformed(ActionEvent ae){

      if(ae.getSource()==red){
         drawColor = Color.red;
      }   
      else if(ae.getSource()==green){
         drawColor = Color.green;
      }
      else if(ae.getSource()==blue){
         drawColor = Color.blue;
      }
      else if(ae.getSource()==black){
         drawColor = Color.black;
      }
      
      else if(ae.getSource()==erase){
         drawColor = Color.white;
      }
   }

   
	public void mouseExited(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){
		record(me.getX(), me.getY());
	}
	public void mousePressed(MouseEvent me){
		record(me.getX(), me.getY());
	}
	public void mouseMoved(MouseEvent me){}
    public void mouseDragged(MouseEvent me){
		int x = me.getX();
		int y = me.getY();
		lines.add(new Line(lastX, lastY, x, y, drawColor));
		record(x, y);
		repaint();
    }
	public void paint(Graphics g){
		for(Line line: lines){
			g.drawLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
         g.setColor(line.getColor());//
      }
	}
	protected void record(int x, int y) {
		lastX = x;
		lastY = y;
	}
}
class Line{

	int startx, starty, endx, endy;
   Color c;//

	public Line(){}
	public Line(int startx, int starty, int endx, int endy, Color c){
		setStartX(startx);
		setStartY(starty);
		setEndX(endx);
		setEndY(endy);
      setColor(c);//
	}
	public void setStartX(int startx){
		this.startx = startx;
	}
	public void setStartY(int starty){
		this.starty = starty;
	}
	public void setEndX(int endx){
		this.endx = endx;
	}
	public void setEndY(int endy){
		this.endy = endy;
	}
	public int getStartX(){
		return startx;
	}
	public int getStartY(){
		return starty;
	}
	public int getEndX(){
		return endx;
	}
	public int getEndY(){
		return endy;
	}
   public void setColor(Color c){//
      this.c=c;
   }
   public Color getColor(){//
      return c;
   }
}
