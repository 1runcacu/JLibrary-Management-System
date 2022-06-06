package app.window.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import app.window.component.MenuBar;
import static javax.swing.SpringLayout.*;

public class Frame extends JFrame{
	protected CardLayout card = new CardLayout();
	protected JPanel tree=new JPanel(new FlowLayout(FlowLayout.LEFT)),show=new JPanel(card),foot=new JPanel(new BorderLayout());
	protected JSplitPane body,box;
	public Frame(String name){
		super(name);
		initFrame(true);
	}
	public Frame(String name,boolean mode){
		super(name);
		initFrame(mode);
	}
	private void initFrame(boolean mode){
		setForm();
		tree.setBackground(Color.WHITE);
		show.setBackground(Color.WHITE);
		foot.setBackground(Color.WHITE);
		body=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,tree,show);
		body.setDividerLocation(120);
		body.setOneTouchExpandable(true);
		body.setContinuousLayout(true);
		if(mode){
			SpringLayout springLayout = new SpringLayout();
			JLabel lb=new JLabel("日志信息");			
			JPanel mbox=new JPanel(springLayout);
			mbox.add(lb);
			mbox.setBackground(Color.WHITE);
	        springLayout.putConstraint(NORTH, lb, 0, NORTH, mbox);
	        springLayout.putConstraint(WEST, lb, 0, WEST, mbox);
	        JScrollPane jp=new JScrollPane(foot);
	        jp.setBorder(null);
	        mbox.add(jp);
	        springLayout.putConstraint(NORTH, jp, 20, NORTH, mbox);
	        springLayout.putConstraint(WEST, jp, 20, WEST, mbox);
	        springLayout.putConstraint(EAST, jp, 0, EAST, mbox);
	        springLayout.putConstraint(SOUTH, jp, 0, SOUTH, mbox);
			box=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,body,mbox);
			box.setDividerLocation(800);
			box.setOneTouchExpandable(true);
			box.setContinuousLayout(true);
			add(box);
		}else{
			add(body);
		}
	}
	private void setForm(){
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(600,450));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//EXIT_ON_CLOSE
	}
	public void reFresh(){
		repaint();
		setVisible(true);
	}
	public void showPage(String name){
		card.show(show,name);
	}
}
