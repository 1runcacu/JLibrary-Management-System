package app.utills;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String ;

public class LogsData extends Data{
	private static String src="data/Logs.csv";
	public static String title[]= {"时间","类型","消息"};
	public LogsData(){
		super(src);
	}
	public static void init(){
		Check.create(src, title);
	}
	public void load(){
		
	}
	public static String getTime(){
		Date date=new Date(System.currentTimeMillis());
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	public void Log(String kind,String msg){
		String args[]= {getTime(),kind,msg};
		push(args);
	}
	public void Log(String msg){
		Log("系统",msg);
	}
	public String toString(int index){
		if(index==0) {
			return "欢迎使用本系统！！！";
		}
		ArrayList<String> c = rst.get(index);
		return c.get(0)+"--"+c.get(1)+":    "+c.get(2);
	}
	public void logShow(String kind,String msg) {
		
	}
	public void logSave() {
		
	}
	public void saveInfer(){
		
	}
	public String name() {
		return "日志表";
	}
	public void logAdd(String args[]){}
}
