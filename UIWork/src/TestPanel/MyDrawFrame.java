package TestPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyDrawFrame extends Frame{

	public MyDrawFrame(){
		setSize(500, 500);
		setTitle("Drawing is Fun");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		MyDrawPanel mdp = new MyDrawPanel();
		add(mdp);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		

	}

}

class MyDrawPanel extends Panel
{
	protected int lastX=0, lastY=0;
	
	public MyDrawPanel()
	{
		setBackground(Color.WHITE);
		addMouseListener(new PositionRecorder());
		addMouseMotionListener(new LineDrawer());
		
	}
	private class PositionRecorder extends MouseAdapter{
		public void mouseEntered(MouseEvent evt){
			record(evt.getX(), evt.getY());
		}
		public void mousePressed(MouseEvent evt){
			record(evt.getX(), evt.getY());
		}
	}
	private class LineDrawer extends MouseMotionAdapter{
		public void mouseDragged(MouseEvent evt){
			int x = evt.getX();
			int y = evt.getY();
			getGraphics().drawLine(lastX, lastY, x, y);
			record(x, y);
		}
	}
	protected void record(int x, int y)
	{
		lastX = x;
		lastY = y;
	}
}
