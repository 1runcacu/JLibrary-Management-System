package app.window.component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

import app.utills.LogsData;

import javax.swing.*;
public class Logs extends JList{
	ArrayList<String> msg=new ArrayList<>();
	private DefaultListModel dlmIns = new DefaultListModel();
	protected LogsData logsD;
	public Logs(LogsData msg){
		super();
		logsD=msg;
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
	public void add(String kind,String msg){
		logsD.Log(kind,msg);
		add(logsD.toString(logsD.rst.size()-1));
	}
	public void	add(String element){
		dlmIns.addElement(element);
		setModel(dlmIns);
	}
	public void initLog(){
		dlmIns.clear();
		for(int i=0;i<logsD.rst.size();i++){
			dlmIns.addElement(logsD.toString(i));
		}
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
	public void save(){
		logsD.save();
	}
}
