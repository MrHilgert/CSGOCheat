package ru.hilgert.jhcore;

public class JMemory {

	public native float readFloat(int address);

	public native double readDouble(int address);

	public native int readInt(int address);

	public native long readLong(int address);

	public native byte readByte(int address);

	public native short readShort(int address);

	public native boolean readBoolean(int address);

	public native String readString(int address, int len);

	public native void writeFloat(int address, float data);

	public native void writeDouble(int address, double data);

	public native void writeInt(int address, int data);

	public native void writeLong(int address, long data);

	public native void writeByte(int address, byte data);

	public native void writeShort(int address, short data);

	public native void writeBoolean(int address, boolean data);

	public native void writeString(int address, String data, int len);

	public native int findPattern(String mask, int startAddress, int size, int... knowValues);

	public int findPattern(String mask, int startAddress, int... knowValues) {
		return findPattern(mask, startAddress, knowValues.length, knowValues);
	}

}
