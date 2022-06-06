package app.window.component;
import javax.swing.JMenuItem;

public class Menu extends JMenuItem{
	final String id;
	public Menu(String str){
		super(str);
		id=str;
	}
}
