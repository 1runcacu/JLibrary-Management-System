package app.service;

import app.utills.Check;
import app.utills.CsvReader;
import app.utills.Data;

public abstract class BorrowData extends Data{
	private static String src="data/Borrow.csv";
	public static String title[]= {"操作","人员名称","人员id","书名","书籍id","时间"};
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
		return "借阅信息表";
	}
	public void logAdd(String args[]){}
	public boolean error(String args[]){
		return false;
	}
}

