package app.utills;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Check{
	public static boolean first = true;
	public static boolean run(){
		File folder = new File("data");
		if (!folder.exists() && !folder.isDirectory()) {
		    folder.mkdirs();
		    return true;
		}
		first = false;
		return false;
	}
	public static void create(String path,String args[]){
		File file = new File(path);
		if (!file.exists()) {
		    try {
		    	BufferedWriter writeText = new BufferedWriter(new FileWriter(path));
		    	writeText.write(String.join(",",args));
	            writeText.flush();
	            writeText.close();
		    } catch(IOException e) {
		        e.printStackTrace();
		    }
		}
	}
}
