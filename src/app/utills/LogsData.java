package app.utills;

public class LogsData extends Data{
	private static String src="data/Logs.csv";
	public static String title[]= {"","时间","类型","消息"};
	public LogsData(){
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
