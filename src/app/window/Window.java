package app.window;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import app.window.component.*;
import app.service.*;
import app.window.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.tree.*;

public class Window extends Frame{
	Window win=this;
	protected Logs log;
	String title[][]= {{
		"信息展示","图书信息","人员信息","借阅信息"
	},{
		"信息录入","图书录入","人员录入","书籍借阅"
	}};
	protected Page page1,page2;
	protected Index index;
	protected MenuBar menubar;
	protected BookData bookdata;
	protected PeopleData peopledata;
	protected BorrowData borrowdata;
	public Window(){
		super("图书管理系统");
		initElement();
		initWindow();
		reFresh();
	}
	private void initElement(){
		bookdata = new BookData();
		peopledata = new PeopleData();
		borrowdata = new BorrowData();
		log = new Logs();
		page1=new Page(title[0]){
			public void select(ChangeEvent e,String tag){
				$selIndex(tag);
			}
		};
		page1.book.add(new SearchField(bookdata),BorderLayout.CENTER);
		page1.people.add(new SearchField(peopledata),BorderLayout.CENTER);
		page1.borrow.add(new SearchField(borrowdata),BorderLayout.CENTER);
		
		page2=new Page(title[1]){
			public void select(ChangeEvent e,String tag){
				$selIndex(tag);
			}
		};
		index = new Index(new DefaultMutableTreeNode("选项卡")){
			public void select(TreeSelectionEvent e,String str){
				$selLeaf(str);
			}
			public void toggle(TreeSelectionEvent e,String str){
				$selNode(str);
			}
		};
		menubar=new MenuBar(){
			public void click(ActionEvent e,String evt) {
				switch(evt){
					case "退出":
						System.exit(DISPOSE_ON_CLOSE);
					case "版本":
						new Toast(win,"版本信息","<html><b>Version 1.0</b><br/>作者:曾柏滔</html>",30,120);
						break;
					case "更多":
						new Toast(win,"更多内容","<html><b>这是要加钱der~</b></html>",200,80);
						break;
					default:
						System.out.println(evt);
						log.add(evt+"被点击了！！！");
				}
			}
		};
	}
	private void initWindow(){
		setJMenuBar(menubar);
		tree.add(index);
		show.add(page1,"信息展示");
		show.add(page2,"信息录入");
		foot.add(log);
	}
	private void $selNode(String str){
		showPage(str);
	}
	private void $selLeaf(String str){
		String pageName =  page1.search(str);
		if(pageName!=null){
			showPage(pageName);
		}else{
			showPage(page2.search(str));
		}
	}
	private void $selIndex(String tag){
		if(index!=null){
			index.selectNode(tag);
		}
	}
}
