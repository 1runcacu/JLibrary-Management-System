package app.window.component;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import app.window.component.Menu;
import java.awt.event.*;

public abstract class MenuBar extends JMenuBar implements ActionListener{
	public final String list[][] = {{
		"系统","新开UI窗口","保存","退出"
	},{
		"关于","版本","更多"
	}};
	public MenuBar(){
		super();
		//new JMenuItem("aaa").addActionListener(new ActionListener() {});
		for(int i=0;i<2;i++){
			JMenu menu=new JMenu(list[i][0]);
			for(int j=1;j<list[i].length;j++){
				Menu item=new Menu(list[i][j]);
				item.addActionListener(this);
				menu.add(item);
			}
			add(menu);
		}
	}
	public abstract void click(ActionEvent e,String evt);
	@Override
	public void actionPerformed(ActionEvent e) {
		String id=((Menu)e.getSource()).id;
		click(e,id);
	}
}
