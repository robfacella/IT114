package TestPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MouseFrame extends Frame
{
	
	public MouseFrame(){
		setSize(500,500);
		setTitle("Mice");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		MousePanel mmp = new MousePanel();
		this.add(mmp);
		
		this.setVisible(true);
	}

	//public MouseFrame(){}
	
	public static void main(String [] args){
		MouseFrame mf = new MouseFrame();
	}
	
}

class MousePanel extends Panel implements MouseListener
{
	MousePanel(){
		addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		System.out.println("Click");
		
		this.setBackground(Color.WHITE);
	}
	@Override
	public void mouseEntered(MouseEvent me) {
		System.out.println("Enter");
		this.setBackground(Color.BLUE);
	}
	@Override
	public void mouseExited(MouseEvent me) {
		System.out.println("Exit");
		this.setBackground(Color.BLACK);
	}
	@Override
	public void mousePressed(MouseEvent me) {
		System.out.println("Press");
		this.setBackground(Color.RED);
	}
	@Override
	public void mouseReleased(MouseEvent me) {
		System.out.println("Release");
		this.setBackground(Color.GREEN);
	}
}