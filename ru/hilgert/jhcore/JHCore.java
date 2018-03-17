package ru.hilgert.jhcore;

public class JHCore {

	static {
		System.loadLibrary("JHCore");
	}

	public static native JProcess openProcess(String processName);

	
	
}