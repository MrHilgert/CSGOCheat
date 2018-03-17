package ru.hilgert.jhcore;

import java.awt.Point;
import java.util.Scanner;

public class JUtils {

	public static native boolean getAsyncKeyState(int keycode);

	public static native void pressUpKey(int keycode);

	public static native void mouseLeftClick(int sleep);

	public static native boolean isMouseButtonDown(int keyCode);

	public static native Point getCursorPos();

	public static native void setCursorPos(int x, int y);

	public static native void setVarValue(int var, int value);

	public static void waitInput() {
		Scanner in = new Scanner(System.in);
		while (in.nextLine() == null);
		in.close();
	}

	public static String toHex(int i) {
		return "0x" + Integer.toHexString(i).toUpperCase();
	}

}
