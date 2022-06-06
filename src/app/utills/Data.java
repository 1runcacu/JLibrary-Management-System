package app.utills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

public abstract class Data extends CsvReader{
	public List<ArrayList<String>> buf;
//	public List<Integer> bufIndex;
	public String showBuf[][];
//	public List<Integer> showIndex[];
	public String tag[];
	public Data(String path){
		super(path);
		initData();
	}
	public void initData(){
		apply();
		ArrayList<String> c;
		c=rst.get(0);
		tag=c.toArray(new String[c.size()]);
		dataRefresh();
	}
	public void apply(){
		buf=rst;
//		bufIndex = new ArrayList<Integer>();
//		for(int i=0;i<rst.size();i++){
//			bufIndex.add(i);
//		}
	}
	public void dataRefresh(){
		ArrayList<String> c;
		showBuf=new String[buf.size()-1][];
//		showIndex=new int[buf.size()-1];
		for(int i=1;i<buf.size();i++){
			c=buf.get(i);
//			showIndex[i]=bufIndex.get(i).intValue();
			showBuf[i-1]=c.toArray(new String[c.size()]);
		}
	}
	public void push(String args[]){
		rst.add((ArrayList<String>) Arrays.asList(args));
	}
	public void delete(int index){
		rst.remove(index+1);
	}
	public void search(String key){
		String words[]=key.split("\\s+");
		List<ArrayList<String>> findRst=new ArrayList<>();
		for(int i=1;i<rst.size();i++){
			if(keyMatch(rst.get(i).toString(),words)){
				findRst.add(rst.get(i));
			}
		}
		buf=findRst;
		dataRefresh();
	}
	public void setValue(int i,int j,String value){
		rst.get(i+1).set(j,value);
//		save();
	}
	public static boolean keyMatch(String inputString, String[] words) {
		StringBuilder regexp = new StringBuilder();
		for (String word : words) {
			regexp.append("(?=.*").append(word).append(")");
		}
		Pattern pattern = Pattern.compile(regexp.toString());
		return pattern.matcher(inputString).find();
	}
	public void reload(){
		refresh();
		initData();
	}
	public abstract void saveAs();
	public abstract String toString(int index);
}
