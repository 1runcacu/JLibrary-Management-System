package app.window;

import app.utills.Data;
import app.window.component.DataIn;
import app.window.component.InputBox;

public class BookIn extends DataIn{
	private InputBox p1,p2,p3,p4,p5,p6;
	public BookIn(Data src) {
		super(src,"录入");
		init();
	}
	public void init(){
		p1 = new InputBox("书名") {
			public void check(String str){
				if(!str.equals("")){
					tik("pass","√");
				}
			}
		};
		p2 = new InputBox("库存"){
			public void check(String str){
				if(!str.equals("")){
					if(legalNum(str)){
						tik("pass","√");
					}else{
						tik("error","库存只能是正数哦~");
					}
				}
			}
		};
		p3 = new InputBox("作者"){
			public void check(String str){
				if(!str.equals("")){
					if(legalName(str)){
						tik("pass","√");
					}else{
						tik("error","作者名称不应该有非法字符哦~");
					}
				}
			}
		};
		p4 = new InputBox("出版社"){
			public void check(String str){
				if(!str.equals("")){
					if(legalName(str)){
						tik("pass","√");
					}else{
						tik("error","出版社不应该有非法字符哦~");
					}
				}
			}
		};
		p5 = new InputBox("类型"){
			public void check(String str){
				if(!str.equals("")){
					if(legalName(str)){
						tik("pass","√");
					}else{
						tik("error","类型不应该有非法字符哦~");
					}
				}
			}
		};
		p6 = new InputBox("描述"){
			public void check(String str){
				tik("pass","√ 描述可以不写哦~");
			}
		};
		body.add(p1);
		body.add(p2);
		body.add(p3);
		body.add(p4);
		body.add(p5);
		body.add(p6);
		body.add(msgBox);
	}
	public String[] gainData() {
		if(p1.flag&p2.flag&p3.flag&p4.flag&p5.flag&p6.flag){
			String data[]= {p1.getText(),Data.code(),p2.getText(),"0",p3.getText(),p4.getText(),p5.getText(),p6.getText()};
			return data;
		}
		return null;
	}
	public void clear(){
		p1.clear();
		p2.clear();
		p3.clear();
		p4.clear();
		p5.clear();
		p6.clear();
		showMsg("");
	}
	public void refresh(){
		p1.cycle();
		p2.cycle();
		p3.cycle();
		p4.cycle();
		p5.cycle();
		p6.cycle();
		showMsg("");
	}
}
