package org.java.mql.ui;

import java.awt.Button;
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

	private Vector<ShapeUml> shapes;
	public Form(int width,int height) {
		super();
		this.width=width;
		this.height=height;
		setBackground(Color.white);
		container = new JPanel();
        add(container);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new TitledBorder(new EtchedBorder(), "jjj  "));
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
	public void addButton(String label, int width, String labelForm,  Map<String, List<Class>> classes) {
		
		container.add(new org.java.mql.ui.Button(label, width, labelForm));
	
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
}
