package app.window;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import app.window.component.*;
import app.service.*;
import app.utills.LogsData;
import app.window.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.tree.*;

public class Window extends Frame{
	Window win=this;
	protected Logs log;
	String title[][]= {{
		"��Ϣչʾ","ͼ����Ϣ","��Ա��Ϣ","������Ϣ"
	},{
		"��Ϣ¼��","ͼ��¼��","��Ա¼��","�鼮����"
	}};
	protected Page page1,page2;
	protected Index index;
	protected MenuBar menubar;
	protected BookData bookdata;
	protected PeopleData peopledata;
	protected BorrowData borrowdata;
	protected LogsData logsD;
	public Window(LogsData msg){
		super("ͼ�����ϵͳ");
		logsD=msg;
		initElement();
		initWindow();
		reFresh();
	}
	private void initElement(){
		log = new Logs(logsD);
		bookdata = new BookData() {
			public void logInfer(){
				log.add("ϵͳ","ͼ����Ϣ����ɹ���");
				log.save();
			}
		};
		peopledata = new PeopleData() {
			public void logInfer(){
				log.add("ϵͳ","��Ա��Ϣ����ɹ���");
				log.save();
			}
		};
		borrowdata = new BorrowData() {
			public void logInfer(){
				log.add("ϵͳ","������Ϣ����ɹ���");
				log.save();
			}
		};
		log.initLog();
		page1=new Page(title[0]){
			public void select(ChangeEvent e,String tag){
				$selIndex(tag);
			}
		};
		page1.book.add(new SearchField(bookdata){
			public void log(String kind,String msg){
				log.add(kind,msg);
			}
		},BorderLayout.CENTER);
		page1.people.add(new SearchField(peopledata){
			public void log(String kind,String msg){
				log.add(kind,msg);
			}
		},BorderLayout.CENTER);
		page1.borrow.add(new SearchField(borrowdata){
			public void log(String kind,String msg){
				log.add(kind,msg);
			}
		},BorderLayout.CENTER);
		
		page2=new Page(title[1]){
			public void select(ChangeEvent e,String tag){
				$selIndex(tag);
			}
		};
		index = new Index(new DefaultMutableTreeNode("ѡ�")){
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
					case "�¿�UI����":
						new Window(logsD);
						log.add("�����µĴ���!");
						break;
					case "�˳�":
						System.exit(DISPOSE_ON_CLOSE);
					case "�汾":
						new Toast(win,"�汾��Ϣ","<html><b>Version 1.0</b><br/>����:������</html>",30,120);
						break;
					case "����":
						new Toast(win,"��������","<html><b>����Ҫ��Ǯder~</b></html>",200,80);
						break;
					case "����":
						bookdata.save();
						peopledata.save();
						borrowdata.save();
						break;
					default:
						System.out.println(evt);
						log.add(evt+"������ˣ�����");
				}
			}
		};
	}
	private void initWindow(){
		setJMenuBar(menubar);
		tree.add(index);
		show.add(page1,"��Ϣչʾ");
		show.add(page2,"��Ϣ¼��");
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
