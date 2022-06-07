package app.window.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import javax.swing.*;

import app.utills.Data;

public abstract class DataIn extends JPanel{
	protected Data src;
	protected JPanel body;
	protected JButton add;
	public DataIn(Data src,String tag){
		super(new BorderLayout());
		this.src=src;
		$init(tag);
	}
	private void $init(String tag){
		body = new JPanel(new VFlowLayout(VFlowLayout.LEFT));
		JPanel box = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		add = new JButton(tag);
		JButton savebtn = new JButton("保存");
		box.add(add);
		box.add(savebtn);
		add(box,BorderLayout.SOUTH);
		JScrollPane jp = new JScrollPane(body);
//		jp.setBorder(null);
		add(jp,BorderLayout.CENTER);
		savebtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				save();
			}
		});
		add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String[] rst = gainData();
				if(rst!=null){
					System.out.println(rst.toString());
					push(rst);
				}else{
					src.showlog("信息输入有误或参数不全，"+src.name()+"新记录录入失败");
				}
			}
		});
	}
	public abstract String[] gainData();
	public void push(String args[]){
		
	}
	public void log(String kind,String msg){
		src.logShow(kind,msg);
	}
	public void save(){
		src.save();
	}
	public void reName(String name) {
		add.setText(name);
	}
}