package app.window;

import java.util.ArrayList;
import java.util.regex.Pattern;

import app.utills.Data;
import app.utills.LogsData;
import app.window.component.DataIn;
import app.window.component.InputBox;
import app.window.component.SearchField;

public class BorrowIn extends DataIn{
	private InputBox p1,p2,p3;
	private SearchField bkd,ppd;
	private String bkn="",ppn="";
	private int count=0;
	private ArrayList<String> peoplename,bookname;
	private DataIn br = this;
	public BorrowIn(Data src,SearchField bkd,SearchField ppd) {
		super(src,"���");
		this.bkd=bkd;
		this.ppd=ppd;
		init();
	}
	public void init(){
		p1 = new InputBox("���Ĳ���"){
			public void check(String str){
				if(str.equals("����")||str.equals("����")){
					tik("pass","��");
				}else{
					tik("error","����ֻ����д['����','����']Ŷ~");
				}
				if(p2!=null){
					p2.cycle();
				}
			}
			public void ref(String data) {
				if(data.equals("����")){
					br.reName("���");
				}else if(data.equals("����")){
					br.reName("�黹");
				}
			}
		};
		p2 = new InputBox("���id"){
			public void check(String str){
				if(!str.equals("")){
					peoplename = ppd.src.rowOf("id",str);
					if(peoplename!=null){
						ppn=peoplename.get(0);
						tik("pass","�� "+ppn);
					}else{
						ppn="";
						tik("error","�� ���޴���");
					}
				}
				if(p3!=null){
					p3.cycle();
				}
			}
		};
		p3 = new InputBox("�鼮id"){
			public void check(String str){
				if(ppn.equals("")){
					tik("?","? ��δ��������Ա���ļ�¼");
					return;
				}
				if(!str.equals("")){
					bookname = bkd.src.rowOf("id",str);
					if(bookname!=null){
						bkn=bookname.get(0);
						count=Integer.parseInt(bookname.get(2));
						String act = p1.getText(),book = p2.getText();
						if(act.equals("����")){
							String mbk = peoplename.get(4).toString();
							if(Pattern.matches(".*"+str+".*",mbk)){
								tik("pass","�� ����黹��"+bkn+"��");
							}else{
								tik("undefinded","* �㲢û�н衶"+bkn+"��");
							}
						}else{
							if(count>0){							
								tik("pass","�� ��"+bkn+"������ʣ��"+ count +"��Ŷ~");
							}else {
								tik("error","�� ��"+bkn+"������û�п����Ŷ~");
							}
						}
					}else{
						tik("error","�� ���޴���");
					}
				}
			}
		};
		body.add(p1);
		body.add(p2);
		body.add(p3);
		body.add(msgBox);
	}
	public String[] gainData() {
		if(p1.flag&p2.flag&p3.flag){
			String data[]= {p1.getText(),ppn,p2.getText(),bkn,p3.getText(),LogsData.getTime()};
			int pi=ppd.src.indexOf("id",data[2])-1,bi=bkd.src.indexOf("id",data[4])-1;
			int out =Integer.parseInt(bookname.get(3));
			String have = peoplename.get(4);
			Pattern p = Pattern.compile("[; |]+");
			String mbk[];
			String nhave="";
			if(have.equals("")){
				mbk= new String[0];
			}else {
				mbk=p.split(have);
			}
			if(data[0].equals("����")){
				bkd.setValue(bi, 2, (--count)+"");
				bkd.setValue(bi, 3, (++out)+"");
				for(int i=0;i<mbk.length;i++){
					nhave+=mbk[i]+"|";
				}
				nhave+=data[4];
				ppd.setValue(pi, 4, nhave);
			}else{
				bkd.setValue(bi, 2, (count+1)+"");
				bkd.setValue(bi, 3, (out-1)+"");
				boolean flag = true;
				String brd = "";
				String pattern = ".*"+data[4]+".*";
				for(int i=0;i<mbk.length;i++){
					String c = "";
					if(i<mbk.length-1){
						c = "|";
					}
					if(flag&&Pattern.matches(pattern, mbk[i])){
						flag=false;
						continue;
					}
					brd+=mbk[i]+c;
				}
				ppd.setValue(pi, 4, brd);
			}
			p3.cycle();
			return data;
		}
		return null;
	}
	public void clear(){
		p1.clear();
		p2.clear();
		p3.clear();
		showMsg("");
	}
	public void refresh(){
		p1.cycle();
		p2.cycle();
		p3.cycle();
		showMsg("");
	}
}
