package app.window.component;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.*;

public abstract class Page extends JTabbedPane {
	public JPanel book,people,borrow;
	String tag[];
	public Page(String title[]){
		super();
		tag=title;
		initPage();
	}
	private void initPage(){
		people=new JPanel(new BorderLayout());book=new JPanel(new BorderLayout());borrow=new JPanel(new BorderLayout());
		people.setBackground(Color.WHITE);
		book.setBackground(Color.WHITE);
		borrow.setBackground(Color.WHITE);
		addTab(tag[1], book);
		addTab(tag[2], people);
		addTab(tag[3], borrow);
		setSelectedComponent(book);
		addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				select(e,find(e));
			}
		});
	}
	public String search(String name){
		for(int i=1;i<tag.length;i++){
			if(name.equals(tag[i])){
				switch(i) {
					case 1:
						setSelectedComponent(book);
						break;
					case 2:
						setSelectedComponent(people);
						break;
					case 3:
						setSelectedComponent(borrow);
						break;
				}
				return tag[0];
			}
		}
		return null;
	}
	private String find(ChangeEvent e){
		int i=((Page)e.getSource()).getSelectedIndex();
		return tag[i+1];
	}
	public abstract void select(ChangeEvent e,String tag);
}
