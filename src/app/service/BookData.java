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
	public void saveInfer(){
		logInfer();
	}
	public String name() {
		return "ͼ����Ϣ��";
	}
}
