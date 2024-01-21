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
	        g.fillRect(x, y + labelHeight, width, height);

	        g.setColor(Color.black);
	        g.drawRect(x, y, 55, labelHeight);
	        int stringY = y + height - 5;
	        g.drawString(namePackage, x + width / 2 - g.getFontMetrics().stringWidth(namePackage) / 2, stringY);

	        g.drawLine(x, y + labelHeight, x + width, y + labelHeight);

	        int classY = y + stringY + 10; 
	        for (Class aClass : nameClasses) {
	            g.drawString(aClass.getSimpleName(), x + 10, classY);
	            classY += 20; 
	        }
	    
      }

}
