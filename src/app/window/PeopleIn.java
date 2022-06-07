package app.window;

import app.utills.Data;
import app.utills.LogsData;
import app.window.component.DataIn;
import app.window.component.InputBox;

public class PeopleIn extends DataIn{
	private InputBox p1,p2,p3;
	public PeopleIn(Data src) {
		super(src,"录入");
		init();
	}
	public void init(){
		p1 = new InputBox("名称"){
			public void check(String str){
				if(!str.equals("")){
					if(legalName(str)){
						tik("pass","√");
					}else{
						tik("error","名称不应该有非法字符哦~");
					}
				}
			}
		};
		p2 = new InputBox("身份"){
			public void check(String str){
				if(!str.equals("")){
					if(legalName(str)){
						tik("pass","√");
					}else{
						tik("error","身份不应该有非法字符哦~");
					}
				}
			}
		};
		p3 = new InputBox("介绍"){
			public void check(String str){
				tik("pass","√ 介绍可以不写哦~");
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
