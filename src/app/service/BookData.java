package app.service;

import app.utills.*;

public abstract class BookData extends Data{
	private static String src="data/Book.csv";
	public static String title[]= {"����","id","���","���","����","������","����","����"};
	public BookData(){
		super(src);
	}
	public static void init(){
		Check.create(src, title);
	}
	public String toString(int index){
		return "";
	}
	public String name() {
		return "ͼ����Ϣ��";
	}
	public void logAdd(String args[]){}
	public boolean error(String args[]){
		return false;
	}
}
