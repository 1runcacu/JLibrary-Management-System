package app.service;

import app.utills.Check;
import app.utills.CsvReader;
import app.utills.Data;

public class BorrowData extends Data{
	private static String src="data/Borrow.csv";
	public static String title[]= {"����","��Ա����","��Աid","����","�鼮id","ʱ��"};
	public BorrowData(){
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
