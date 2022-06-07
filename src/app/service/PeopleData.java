package app.service;

import app.utills.Check;
import app.utills.CsvReader;
import app.utills.Data;

public abstract class PeopleData extends Data{
	private static String src="data/People.csv";
	public static String title[]= {"名称","id","身份","介绍","已借书籍id"};
	public PeopleData(){
		super(src);
	}
	public static void init(){
		Check.create(src, title);
	}
	public String toString(int index){
		return "";
	}
	public String name() {
		return "人员信息表";
	}
	public void logAdd(String args[]){
		
	}
	public boolean error(String args[]){
		return false;
	}
}
