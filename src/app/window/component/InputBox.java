package app.window.component;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InputBox extends JPanel{
	private static FlowLayout fl = new FlowLayout(FlowLayout.LEFT,10,0);
	JTextField words = new JTextField(18);
	JLabel hint = new JLabel("");
	String tag="未知";
	public boolean flag=false;
	public InputBox(String tag){
		super(fl);
		this.tag=tag;
		init(tag);
	}
	private void init(String tag){
		Font f = new Font("宋体",Font.PLAIN,14);
		hint.setFont(f);
        add(words);
        add(hint);
        action("hint");
        $check("");
        words.addFocusListener(new FocusListener() {
        	public void focusLost(FocusEvent e){
		    	String temp = words.getText();
				if(temp.equals("")) {
					action("hint");
				}
		    }
		    public void focusGained(FocusEvent e) {
				//获取焦点时，清空提示内容
				String temp = words.getText();
				if(temp.equals(tag)) {
					action("clear");
				}
			}
        });
		words.getDocument().addDocumentListener(new DocumentListener() {
		    public void insertUpdate(DocumentEvent e) {
		    	$check(words.getText());
		    }
		    public void removeUpdate(DocumentEvent e) {
		    	$check(words.getText());
		    }
		    public void changedUpdate(DocumentEvent e) {
		    	$check(words.getText());
		    }
	    });
	}
	private void action(String act){
		switch(act) {
			case "clear":
				words.setText("");
				words.setForeground(Color.BLACK);
				break;
			case "hint":
				words.setText(tag);
				words.setForeground(Color.GRAY);
				break;
			default:
		}
	}
	public void tik(String mode,String msg){
		flag=false;
		hint.setText(msg);
		switch(mode) {
			case "error":
				hint.setForeground(Color.RED);
				break;
			case "pass":
				flag=true;
				hint.setForeground(new Color(30,170,20));
				break;
			default:
				hint.setForeground(new Color(50,50,50));
				break;
		}
	}
	public void $check(String str){
		if(str.equals(tag)){
			return;
		}else if(str.equals("")){
			tik("untitled","× 该项不能为空哦~");
			check(str);
		}else{
			if(legalKey(str)){
				tik("pass","√");
				check(str);
			}else{
				tik("error","含有非法字符哦~");
			}
		}
	}
	public static boolean legalKey(String str){
		String pattern = "^(?!_)(?!.*?_$)[a-zA-Z0-9_~!！·-，..#@?、？\u4e00-\u9fa5]+$";
		return Pattern.matches(pattern, str);
	}
	public static boolean legalName(String str){
		String pattern = "^(?!_)(?!.*?_$)[a-zA-Z_\u4e00-\u9fa5]+$";
		return Pattern.matches(pattern, str);
	}
	public static boolean legalNum(String str){
		String pattern = "^[1-9]+\\d*$";
		return Pattern.matches(pattern, str);
	}
	public void check(String str){
		
	}
	public String getText(){
		String data = words.getText();
		if(data.equals(tag)){
			return "";
		}
		return data;
	}
}
