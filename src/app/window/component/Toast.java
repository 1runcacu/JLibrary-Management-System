package app.window.component;

import java.awt.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Toast extends JDialog{
	public Toast(JFrame frame,String name,String msg,int wid,int hei){
		super(frame,name);
		JLabel lb=new JLabel(msg,JLabel.CENTER);
		add(lb);
		setSize(wid,hei);
		setCenter(frame);
		setResizable(false);
		setVisible(true);
	}
	private void setCenter(JFrame frame){
//		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = frame.getX() + frame.getWidth()/2 - getWidth()/2;
		int y = frame.getY() + frame.getHeight()/2 - getHeight()/2;
		setLocation(x, y);
	}
}
