package app.service;

import app.utills.Check;
import app.utills.CsvReader;
import app.utills.Data;

public class PeopleData extends Data{
	private static String src="data/People.csv";
	public static String title[]= {"����","id","���","����","����","�ѽ��鼮id"};
	public PeopleData(){
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
