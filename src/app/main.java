package app;

import app.window.*;
import app.service.*;
import app.utills.*;


public class main {
	public static void main(String[] args) {
		runSys();
	}
	private static void runSys(){
		Check.run();
		BookData.init();
		PeopleData.init();
		BorrowData.init();
		LogsData.init();
		LogsData msg = new LogsData();
		new Window(msg);
	}
}
