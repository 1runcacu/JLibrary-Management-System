package app.utills;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public abstract class CsvReader{
	public String path;
	public List<ArrayList<String>> rst;
	public CsvReader(String path){
		this.path=path;
		refresh();
	}
	public void refresh(){
		rst = new ArrayList<>();
		read();
	}
	private String reset(String str) throws UnsupportedEncodingException {
//		System.out.println(str);
		return str;
//		return new String(str.getBytes(),"UTF-8");
	}
	protected void read(){
		String line = "";
        String cvsSplitBy = ",";
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null){;
                ArrayList<String> data = new ArrayList<String>();
                Collections.addAll(data, reset(line).split(cvsSplitBy));
                rst.add(data);
            }
        } catch (IOException e) {
        	System.out.println(e);
        }
	}
	protected void write(String path){
		File csv = new File(path);
		try{
            BufferedWriter writeText = new BufferedWriter(new FileWriter(csv));
            for(int i=0;i<rst.size();i++){
            	writeText.write(String.join(",",rst.get(i)));
            	writeText.newLine();
            }
            writeText.flush();
            writeText.close();
        }catch (IOException e){
            System.out.println(e);
        }
	}
	public void push(String args[]){
		rst.add((ArrayList<String>) Arrays.asList(args));
	}
	public void show(){
		System.out.println(rst);
	}
	public void save(){
		write(path);
		saveInfer();
	}
	public void save(String path){
		write(path);
	}
	public abstract void saveInfer();
}
