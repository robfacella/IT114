import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MyNewDrawFrame extends Frame{
	
	public MyNewDrawFrame(){
			setSize(500,500);
			setTitle("Drawing is for losers");
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent we){
					System.exit(0);
				}
			});
			MyNewDrawPanel mdp = new MyNewDrawPanel();
			add(mdp);
			setVisible(true);
	}
	public static void main(String[] args){
		MyNewDrawFrame mdf = new MyNewDrawFrame();
	}
}
class MyNewDrawPanel extends DoubleBuffer implements MouseListener, MouseMotionListener, ActionListener{

	int lastX=0, lastY=0;
	ArrayList<Line>lines;
   Color drawColor;
   Button red, green, blue, black, clear, erase;

	public MyNewDrawPanel() {
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
      clear = new Button("Clear");
      clear.addActionListener(this);
      erase = new Button("Erase");
      erase.addActionListener(this);
      DoubleBuffer p = new DoubleBuffer();
      p.add(red);
      p.add(green);
      p.add(blue);
      p.add(black);
      p.add(clear);
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
      else if(ae.getSource()==clear){
         Graphics g = this.getGraphics(); 
         Rectangle r = this.bounds(); 
         g.setColor(this.getBackground()); 
         g.fillRect(r.x, r.y, r.width, r.height); 
         
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