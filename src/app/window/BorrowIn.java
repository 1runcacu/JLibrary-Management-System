package app.window;

import app.utills.Data;
import app.utills.LogsData;
import app.window.component.DataIn;
import app.window.component.InputBox;

public class BorrowIn extends DataIn{
	private InputBox p1,p2,p3;
	public BorrowIn(Data src) {
		super(src,"���");
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
			}
		};
		p2 = new InputBox("���id");
		p3 = new InputBox("�鼮id");
		body.add(p1);
		body.add(p2);
		body.add(p3);
	}
	public String[] gainData() {
		if(p1.flag&p2.flag&p3.flag){
			String data[]= {p1.getText(),Data.code(),p2.getText(),p3.getText(),"",LogsData.getTime()};
			return data;
		}
		return null;
	}
}
