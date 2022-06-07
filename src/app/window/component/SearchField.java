package app.window.component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;
import app.utills.Data;
import javax.swing.event.*;
import javax.swing.table.*;
import static javax.swing.SpringLayout.*;

public abstract class SearchField extends JPanel{
	protected Data src;
	protected JTable table;
	private DefaultTableModel model;
	private TableRowSorter sorter;
	public SearchField(Data src){
		super(new BorderLayout());
		this.src=src;
		initSearch();
	}
	private void initSearch(){
		model = new DefaultTableModel(src.showBuf,src.tag);
		sorter = new TableRowSorter<>(model);
		table=new JTable(model);
		table.setRowSorter(sorter);
//		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.WHITE);
		JScrollPane jp = new JScrollPane(table);
		jp.setBorder(null);
		add(jp);
		JPanel cBox = new JPanel(new BorderLayout());
		JPanel sBox = new JPanel(new BorderLayout());
		add(cBox,BorderLayout.SOUTH);
		JTextField keyWords = new JTextField(15);
		keyWords.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		      search(keyWords.getText());
		    }
		    @Override
		    public void removeUpdate(DocumentEvent e) {
		      search(keyWords.getText());
		    }
		    @Override
		    public void changedUpdate(DocumentEvent e) {
		      search(keyWords.getText());
		    }
		    public void search(String str) {
		    	if (str.length() == 0){
		    		sorter.setRowFilter(null);
		    	} else {
		    		sorter.setRowFilter(RowFilter.regexFilter(str));
		    	}
		   }
	    });
		JLabel query=new JLabel("����: ");
		JPanel btnBox=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
		final JButton save=new JButton("����");
		final JButton delete=new JButton("ɾ��");
		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				src.save();
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int[] selectedRows = table.getSelectedRows();
				for (int row = 0; row < selectedRows.length; row++) {
					selectedRows[row]=sorter.convertRowIndexToModel(selectedRows[row]);
				}
				Arrays.sort(selectedRows);
				for (int row = selectedRows.length-1; row >-1;row--) {
					model.removeRow(selectedRows[row]);
				}
				table.clearSelection();
				log("ϵͳ","�ɹ�ɾ����"+src.name()+"��"+selectedRows.length+"�����ݣ�");
			}
		});
		btnBox.add(delete);
		btnBox.add(save);
		sBox.add(query,BorderLayout.WEST);
		sBox.add(keyWords,BorderLayout.EAST);
		cBox.add(sBox,BorderLayout.WEST);
		cBox.add(btnBox,BorderLayout.EAST);
		model.addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent e){
				int type = e.getType();
				int row = -1,col = e.getColumn();
				try {
					row = sorter.convertRowIndexToModel(table.getSelectedRow());
		        } catch (Exception error){
//		            error.printStackTrace();
		        }
				switch(type){
					case TableModelEvent.INSERT:
						row=model.getRowCount()-1;
						src.push(model.getDataVector().get(row));
						log("ϵͳ",src.name()+"�гɹ�¼����"+"һ������");
						break;
					case TableModelEvent.UPDATE:
						String value = (String)model.getValueAt(row,col);
						src.setValue(row,col,value);
						log("ϵͳ","����"+src.name()+"�е�"+row+"�� "+col+"��Ϊ:'"+value+"'");
						table.clearSelection();
						break;
					case TableModelEvent.DELETE:
						src.delete(row);
						break;
				}
			}
		});
	}
	public void push(String data[]){
		model.addRow(data);
	}
	public abstract void log(String kind,String msg);
}
