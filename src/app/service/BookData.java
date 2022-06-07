package app.service;

import app.utills.*;

public abstract class BookData extends Data{
	private static String src="data/Book.csv";
	public static String title[]= {"书名","id","库存","借出","作者","出版社","类型","描述"};
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
		return "图书信息表";
	}
	public void logAdd(String args[]){}
	public boolean error(String args[]){
		return false;
	}
}
