package app.service;

import app.utills.Check;
import app.utills.CsvReader;
import app.utills.Data;

public abstract class PeopleData extends Data{
	private static String src="data/People.csv";
	public static String title[]= {"����","id","���","����","�ѽ��鼮id"};
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
		return "��Ա��Ϣ��";
	}
	public void logAdd(String args[]){
		
	}
	public boolean error(String args[]){
		return false;
	}
}
