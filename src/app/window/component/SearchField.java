package app.window.component;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;
import app.utills.Data;
import javax.swing.event.*;
import javax.swing.table.*;
import static javax.swing.SpringLayout.*;

public class SearchField extends JPanel{
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
		add(new JScrollPane(table));
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
		    		System.out.println(RowFilter.regexFilter(str));
		    		sorter.setRowFilter(RowFilter.regexFilter(str));
		    	}
		   }
	    });
		JLabel query=new JLabel("ËÑË÷: ");
		JPanel btnBox=new JPanel(new FlowLayout(10,30,0));
		final JButton save=new JButton("±£´æ");
		final JButton delete=new JButton("É¾³ý");
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
				int row = e.getFirstRow(),col = e.getColumn();
				switch(type){
					case TableModelEvent.INSERT:
						break;
					case TableModelEvent.UPDATE:
						src.setValue(row,col,(String)model.getValueAt(row,col));
						break;
					case TableModelEvent.DELETE:
						src.delete(row);
						break;
				}
			}
		});
	}
}
