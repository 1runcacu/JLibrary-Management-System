package app;

import app.window.*;
import app.service.*;
import app.utills.*;


public class main {
	public static void main(String[] args) {
		runSys();
//		CsvReader csv = new CsvReader("a.csv");
//		csv.show();
//		csv.save("data\\b.csv");
	}
	private static void runSys(){
		Check.run();
		BookData.init();
		PeopleData.init();
		BorrowData.init();
		LogsData.init();
		LogsData msg = new LogsData() {
			
		};
		new Window(msg);
	}
}
