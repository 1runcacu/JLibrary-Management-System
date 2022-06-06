package app.window.component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.*;
public class Logs extends JList{
	private DefaultListModel dlmIns = new DefaultListModel();
	public Logs(){
		super();
		setModel(dlmIns);
//		setVisibleRowCount(100);
		
	}
	public Logs(String list[]){
		super();
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(list);
	}
	public void set(String list[]){
		dlmIns.clear();
		add(list);
	}
	public void	add(String element){
		dlmIns.addElement(element);
		setModel(dlmIns);
	}
	public void	add(String list[]){
		dlmIns.clear();
		for(int i=0;i<list.length;i++){
			dlmIns.addElement(list[i]);
		}
		setModel(dlmIns);
	}
	public void clear(){
		dlmIns.clear();
		setModel(dlmIns); 
	}
}
