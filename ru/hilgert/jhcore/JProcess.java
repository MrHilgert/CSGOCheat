package ru.hilgert.jhcore;

public class JProcess {

	public JProcess() {}

	public native JModule getModule(String modulename);

	public native JGDI getGDI();

	public static JMemory getMemory() {
		return new JMemory();
	}

}
