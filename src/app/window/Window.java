package app.window;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import app.window.component.*;
import app.service.*;
import app.utills.Data;
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
	protected Data bookdata,peopledata,borrowdata;
	protected LogsData logsD;
	protected DataIn bookin,peoplein,borrowin;
	protected SearchField bookfield,peoplefield,borrowfield;
	public Window(LogsData msg){
		super("ͼ�����ϵͳ");
		logsD=msg;
		initElement();
		initWindow();
		reFresh();
	}
	private void initElement(){
		log = new Logs(logsD);
		log.initLog();
		bookdata = new BookData(){
			public void logShow(String kind,String msg) {
				log.add(kind,msg);
			}
			public void logSave() {
				log.save();
			}
			public void showlog(String msg) {
				log.add(msg);
			}
		};
		peopledata = new PeopleData() {
			public void logShow(String kind,String msg) {
				log.add(kind,msg);
			}
			public void logSave() {
				log.save();
			}
			public void showlog(String msg) {
				log.add(msg);
			}
		};
		borrowdata = new BorrowData() {
			public void logShow(String kind,String msg) {
				log.add(kind,msg);
			}
			public void logSave() {
				log.save();
			}
			public void showlog(String msg) {
				log.add(msg);
			}
		};
		page1=new Page(title[0]){
			public void select(ChangeEvent e,String tag){
				$selIndex(tag);
			}
		};
		bookfield=new SearchField(bookdata){
			public void log(String kind,String msg){
				log.add(kind,msg);
			}
		};
		peoplefield=new SearchField(peopledata){
			public void log(String kind,String msg){
				log.add(kind,msg);
			}
		};
		borrowfield=new SearchField(borrowdata){
			public void log(String kind,String msg){
				log.add(kind,msg);
			}
		};
		page1.book.add(bookfield,BorderLayout.CENTER);
		page1.people.add(peoplefield,BorderLayout.CENTER);
		page1.borrow.add(borrowfield,BorderLayout.CENTER);
		bookin = new BookIn(bookdata){
			public void push(String args[]){
				bookfield.push(args);
				showMsg("��"+args[0]+"�������"+args[2]+"��~");
			}
		};
		peoplein = new PeopleIn(peopledata){
			public void push(String args[]){
				peoplefield.push(args);
				showMsg("�����û�"+args[0]+",id:"+args[1]+"");
			}
		};
		borrowin = new BorrowIn(borrowdata,bookfield,peoplefield){
			public void push(String args[]){
				borrowfield.push(args);
				showMsg("�û�"+args[1]+"��"+args[0]+"��"+args[3]+"��1��");
			}
		};
		page2=new Page(title[1]){
			public void select(ChangeEvent e,String tag){
				$selIndex(tag);
			}
		};
		page2.book.add(bookin,BorderLayout.CENTER);
		page2.people.add(peoplein,BorderLayout.CENTER);
		page2.borrow.add(borrowin,BorderLayout.CENTER);
		
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
						look();
						new Toast(win,"�汾��Ϣ","<html><b>Version 1.0</b><br/>����:������</html>",30,120);
						break;
					case "����":
						new Toast(win,"��������","<html><b>���������ټӹ��ܣ�<br/>�ߣ���ȻҪ��Ǯder~</b></html>",200,160);
						break;
					case "����":
						bookdata.save();
						peopledata.save();
						borrowdata.save();
						look();
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
