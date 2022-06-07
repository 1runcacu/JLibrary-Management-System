package app.window;

import app.utills.Data;
import app.utills.LogsData;
import app.window.component.DataIn;
import app.window.component.InputBox;

public class PeopleIn extends DataIn{
	private InputBox p1,p2,p3;
	public PeopleIn(Data src) {
		super(src,"¼��");
		init();
	}
	public void init(){
		p1 = new InputBox("����"){
			public void check(String str){
				if(!str.equals("")){
					if(legalName(str)){
						tik("pass","��");
					}else{
						tik("error","���Ʋ�Ӧ���зǷ��ַ�Ŷ~");
					}
				}
			}
		};
		p2 = new InputBox("���"){
			public void check(String str){
				if(!str.equals("")){
					if(legalName(str)){
						tik("pass","��");
					}else{
						tik("error","��ݲ�Ӧ���зǷ��ַ�Ŷ~");
					}
				}
			}
		};
		p3 = new InputBox("����"){
			public void check(String str){
				tik("pass","�� ���ܿ��Բ�дŶ~");
			}
		};
		body.add(p1);
		body.add(p2);
		body.add(p3);
	}
	public String[] gainData() {
		if(p1.flag&p2.flag&p3.flag){
			String data[]= {p1.getText(),Data.code(),p2.getText(),p3.getText(),""};
			return data;
		}
		return null;
	}
}
