package ru.hilgert.jhcore;

public class JModule extends JMemory {

	private String name;
	private int address;

	public JModule(String name, int address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public int getAddress() {
		return this.address;
	}

}