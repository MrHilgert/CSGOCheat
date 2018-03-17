package ru.hilgert.jhcore;

import java.awt.Color;

public class JGDI {

	public native void setTextAlign(int align);

	public native void setTextColor(int r, int g, int b);

	public native void setBackColor(int r, int g, int b);

	public native void drawText(int x, int y, String text, int len);

	public native void drawRect(int x, int y, int width, int height);

	public native void drawLine(int x, int y, int x1, int y1);

	public void setTextColor(Color color) {
		setTextColor(color.getRed(), color.getGreen(), color.getBlue());
	}

	public void drawText(int x, int y, String text) {
		drawText(x, y, text, text.length());
	}

}
