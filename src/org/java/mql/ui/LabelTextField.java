package org.java.mql.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabelTextField extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LabelTextField(String label) {
		setLayout(new FlowLayout(FlowLayout.LEFT));	
		if(!label.contains(":")) {
			label+=":";
			JLabel l1=new JLabel(label);
			
			add(l1);
		}
	}
	public LabelTextField(String label,int size,String key) {
		setLayout(new FlowLayout(FlowLayout.LEFT));	
		if(!label.contains(":")) {
			label+=":";
			JLabel l1=new JLabel(label);
			JTextField t1=new JTextField(size);
			add(l1);
			add(t1);
		}
	}
	public LabelTextField(String label,int size,int labelSize,String key) {
		this(label,size,key);
		JLabel l1=(JLabel)getComponent(0);
		l1.setPreferredSize(new Dimension(labelSize,l1.getPreferredSize().height));
	}
	public String getTextFieldValue() {
	    JTextField textField = (JTextField) getComponent(1); 
	    return textField.getText();
	}
	

}
