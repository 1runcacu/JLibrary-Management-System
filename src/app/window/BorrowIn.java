package app.window;

import app.utills.Data;
import app.utills.LogsData;
import app.window.component.DataIn;
import app.window.component.InputBox;

public class BorrowIn extends DataIn{
	private InputBox p1,p2,p3;
	public BorrowIn(Data src) {
		super(src,"借出");
		init();
	}
	public void init(){
		p1 = new InputBox("借阅操作"){
			public void check(String str){
				if(str.equals("借书")||str.equals("还书")){
					tik("pass","√");
				}else{
					tik("error","这里只能填写['借书','还书']哦~");
				}
			}
		};
		p2 = new InputBox("身份id");
		p3 = new InputBox("书籍id");
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
