package app.service;

import app.utills.*;

public class BookData extends Data{
	private static String src="data/Book.csv";
	public static String title[]= {"名称","id","库存","借出","作者","出版社","类型","描述"};
	public BookData(){
		super(src);
	}
	public static void init(){
		Check.create(src, title);
	}
	public void saveAs(){
		
	}
	public String toString(int index){
		return "";
	}
}
