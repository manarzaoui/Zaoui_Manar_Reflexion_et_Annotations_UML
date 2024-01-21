package org.java.mql.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



public class Form extends JPanel  {
	private static final long serialVersionUID = 1L;
	private int width,height;
    private JPanel container;
    private LabelTextField textField1;
    private int labelSize = 100;

	private Vector<ShapeUml> shapes;
	public Form(int width,int height) {
		super();
		this.width=width;
		this.height=height;
		setBackground(Color.white);
		container = new JPanel();
        add(container);
        
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new TitledBorder(new EtchedBorder(), "Desinner un diagramme  "));
		setBorder(new LineBorder(Color.blue));
		shapes=new Vector<ShapeUml>();
	}
	public Dimension getPreferredSize() {
		return new Dimension(width,height);
	}
	
	public void add(ShapeUml shpae) {
		shapes.add(shpae);
		repaint();
	}
	public void addButton(String label, int width, String labelForm  ) {
		
		container.add(new Button(label, width, labelForm,textField1));
	
	}

	//gerere les dessins
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		for (ShapeUml shape:shapes) {
			shape.paint(g);
		}
	}
	public void addField(String label, int size,String key) {
        LabelTextField textField = new LabelTextField(label, size, labelSize,key);
        container.add(textField);
        
        if (key.equals("projet")) {
            textField1 = textField;
        }
    }

    public String getTextField1Value() {
        return textField1.getTextFieldValue();
    }
}
