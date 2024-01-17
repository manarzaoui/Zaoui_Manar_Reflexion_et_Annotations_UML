package org.java.mql.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;

public class Package implements ShapeUml {
  private int x,y,width,height;
  private List<Class> nameClasses;
  private String  namePackage;
  private Color color=Color.black;


public Package(int x, int y, int width, int height,String namePackage,List<Class> nameClasses) {
	super();
	this.x = x;
	this.y = y;
	this.width = width;
	this.namePackage=namePackage;
	this.nameClasses=nameClasses;
	this.height = height;
}
public void paint(Graphics g) {
	 
	int labelHeight = 20;
	 g.setColor(Color.lightGray);
	 //dessiner Rectangle
     g.fillRect(x, y+labelHeight, width, height);

     // Draw package label
     
     g.setColor(Color.black);
     //dessiner conteur
     g.drawRect(x, y, 55, labelHeight); // Draw a rectangle for the label
     int stringY = y + height - 5; // Adjust the margin as needed
     g.drawString(namePackage, x + width / 2 - g.getFontMetrics().stringWidth(namePackage) / 2, stringY);

     // Draw lines to represent the package
     g.drawLine(x, y + labelHeight, x + width, y + labelHeight); // Horizontal line at the bottom of the label

}

}
