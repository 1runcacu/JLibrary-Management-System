package app.utills;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CsvReader{
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
	protected void read(){
		String line = "";
        String cvsSplitBy = ",";
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null){;
                ArrayList<String> data = new ArrayList<String>();
                Collections.addAll(data, line.split(cvsSplitBy));
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
		System.out.println("aaaa");
	}
	public void show(){
		System.out.println(rst);
	}
	public void save(){
		write(path);
	}
	public void save(String path){
		write(path);
	}
}
