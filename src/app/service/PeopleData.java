package app.service;

import app.utills.Check;
import app.utills.CsvReader;
import app.utills.Data;

public abstract class PeopleData extends Data{
	private static String src="data/People.csv";
	public static String title[]= {"名称","id","身份","介绍","作者","已借书籍id"};
	public PeopleData(){
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
		return "人员信息表";
	}
}
