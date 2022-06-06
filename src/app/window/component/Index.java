package app.window.component;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
public abstract class Index extends JTree implements TreeSelectionListener{
	String[][] item={
		{"信息展示","图书信息","人员信息","借阅信息"},
		{"信息录入","图书录入","人员录入","书籍借阅"}
	};
	DefaultMutableTreeNode treeNode[][]=new DefaultMutableTreeNode[2][4];
	public Index(DefaultMutableTreeNode root){
		super(root);
		for (int i=0;i<item.length;i++){
			treeNode[i][0]=new DefaultMutableTreeNode(item[i][0]);
			root.add(treeNode[i][0]);
			for (int j=1;j<item[i].length;j++){
				treeNode[i][j]=new DefaultMutableTreeNode(item[i][j]);
				treeNode[i][0].add(treeNode[i][j]);
			}
		}
		expandAll(this,true);
		addTreeSelectionListener(this);
		selectNode("图书信息");
	}
	public void valueChanged(TreeSelectionEvent e){
		if(e.getSource()==this){
			DefaultMutableTreeNode node=(DefaultMutableTreeNode)getLastSelectedPathComponent();
			String str=node.toString();
			if(node.isLeaf()){
				select(e,str);
			}else{
				toggle(e,str);
			}
		}
	}
	public void expandAll(JTree tree, boolean expand) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		expandAll(tree, new TreePath(root), expand);
	}
	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node!=null&&node.getChildCount() >= 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}
	public void selectNode(String node){
		TreePath tp = searchNode(node);
		if(tp!=null){
			setSelectionPath(tp);
		}
	}
	public TreePath searchNode(String name){
		for(int i=0;i<treeNode.length;i++){
			for(int j=0;j<treeNode[i].length;j++){
				if(treeNode[i][j].toString().equals(name)){
					return new TreePath(treeNode[i][j].getPath());
				}
			}
		}
		return null;
	}
	public abstract void select(TreeSelectionEvent e,String str);
	public abstract void toggle(TreeSelectionEvent e,String str);
}
