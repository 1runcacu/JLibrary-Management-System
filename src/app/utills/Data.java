package app.utills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.regex.*;

public abstract class Data extends CsvReader{
//	public List<ArrayList<String>> buf;
//	public List<Integer> bufIndex;
//	public String showBuf[][];
	public Vector showBuf = new Vector<>();
//	public List<Integer> showIndex[];
	public Vector tag;
	public Data(String path){
		super(path);
		initData();
	}
	public void initData(){
		apply();
		ArrayList<String> c;
		c=rst.get(0);
		tag = new Vector<>();
		for(int i=0;i<rst.get(0).size();i++){
			tag.add(rst.get(0).get(i));
		}
//		tag=c.toArray(new String[c.size()]);
		dataRefresh();
	}
	public void apply(){
//		buf=rst;
//		bufIndex = new ArrayList<Integer>();
//		for(int i=0;i<rst.size();i++){
//			bufIndex.add(i);
//		}
	}
	public String gain(int i,int j){
		return rst.get(i).get(j).toString();
	}
	public void dataRefresh(){
		showBuf.clear();
		for(int i=1;i<rst.size();i++){
			Vector rowV = new Vector<>();
			ArrayList<String> c = rst.get(i);
			for(int j=0;j<tag.size();j++){
				if(j<c.size()){
					rowV.add(c.get(j));
				}else{
					rowV.add("");
					c.add("");
				}
			}
			showBuf.add(rowV);
		}
//		ArrayList<String> c;
//		showBuf=new String[buf.size()-1][];
////		showIndex=new int[buf.size()-1];
//		for(int i=1;i<buf.size();i++){
//			c=buf.get(i);
////			showIndex[i]=bufIndex.get(i).intValue();
//			showBuf[i-1]=c.toArray(new String[c.size()]);
//		}
	}
	public void push(String args[]){
		ArrayList<String> c = new ArrayList<>();
		for(int i=0;i<tag.size();i++){
			if(i<args.length){
				c.add(args[i]);
			}else{
				c.add("");
			}
		}
		rst.add(c);
	}
	public void push(Vector args){
		ArrayList<String> c = new ArrayList<String>(args);
		while(c.size()<tag.size()) {
			c.add("");
		}
		rst.add(c);
	}
	public void delete(int index){
		rst.remove(index+1);
	}
	public int indexOf(String key,String value){
		int index=-1;
		for(int i=0;i<tag.size();i++){
			if(tag.get(i).toString().equals(key)){
				index=i;
				break;
			}
		}
		if(index>-1){
			for(int i=1;i<rst.size();i++){
				if(rst.get(i).get(index).toString().equals(value)){
					return i;
				}
			}
		}
		return -1;
	}
	public ArrayList<String> rowOf(String key,String value){
		int index=-1;
		for(int i=0;i<tag.size();i++){
			if(tag.get(i).toString().equals(key)){
				index=i;
				break;
			}
		}
		if(index>-1){
			for(int i=1;i<rst.size();i++){
				if(rst.get(i).get(index).toString().equals(value)){
					return rst.get(i);
				}
			}
		}
		return null;
	}
	public void search(String key){
		String words[]=key.split("\\s+");
		List<ArrayList<String>> findRst=new ArrayList<>();
		for(int i=1;i<rst.size();i++){
			if(keyMatch(rst.get(i).toString(),words)){
				findRst.add(rst.get(i));
			}
		}
//		buf=findRst;
//		dataRefresh();
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
	public void saveInfer(){
		logShow("系统",name()+"保存成功！");
		logSave();
	}
	public void addData(String args[]){
		if(error(args)) return;
		ArrayList<String> c=new ArrayList<>();
		for(int i=0;i<args.length;i++){
			c.add(args[i]);
		}
		rst.add(c);
		logAdd(args);
	}
	public abstract String name();
	public abstract void logShow(String kind,String msg);
	public abstract void logSave();
	public abstract void logAdd(String args[]);
	public boolean error(String args[]){
		return false;
	}
	public abstract String toString(int index);
	public int find(int key,String value){
		return -1;
	}
	public static String code(int length){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random(System.currentTimeMillis());
	     StringBuffer sb=new StringBuffer();
	     for(int i=1;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return "#"+sb.toString();
	}
	public static String code(){
		return code(8);
	}
	public void showlog(String msg){}
}
