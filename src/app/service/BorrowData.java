package app.service;

import app.utills.Check;
import app.utills.CsvReader;
import app.utills.Data;

public abstract class BorrowData extends Data{
	private static String src="data/Borrow.csv";
	public static String title[]= {"����","��Ա����","��Աid","����","�鼮id","ʱ��"};
	public BorrowData(){
		super(src);
	}
	public static void init(){
		Check.create(src, title);
	}
	public String toString(int index){
		return "";
	}
	public String name() {
		return "������Ϣ��";
	}
	public void logAdd(String args[]){}
	public boolean error(String args[]){
		return false;
	}
}

