package org.java.mql.ui;

import java.awt.Color;
import java.awt.Graphics;

public class MyClass implements ShapeUml {
  private int x,y,width,height;
  private String className;
  private Color color=Color.black;


public MyClass(int x, int y, int width, int height, String className) {
	super();
	this.x = x;
	this.y = y;
	this.width = width;
	this.className=className;
	this.height = height;
}
  public void paint(Graphics g) {
	  g.setColor(color);
	  g.drawRect(x, y, width, height);
      int textX = x + width / 2 - g.getFontMetrics().stringWidth(className) / 2;
      int textY = y + height / 2 + g.getFontMetrics().getHeight() / 4;

      g.drawString(className, textX, textY);
  }
}
