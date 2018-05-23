package ru.otus.homework08.model;

public class ObjectWithEightPrimitiveArrays {
    private boolean[] booleanArray;
    private char[] charArray;
    private float[] floatArray;
    private double[] doubleArray;

    private byte[] byteArray;
    private short[] shortArray;
    private int[] intArray;
    private long[] longArray;

    public ObjectWithEightPrimitiveArrays(boolean[] booleanArray, char[] charArray, float[] floatArray, double[] doubleArray, byte[] byteArray, short[] shortArray, int[] intArray, long[] longArray) {
        this.booleanArray = booleanArray;
        this.charArray = charArray;
        this.floatArray = floatArray;
        this.doubleArray = doubleArray;
        this.byteArray = byteArray;
        this.shortArray = shortArray;
        this.intArray = intArray;
        this.longArray = longArray;
    }

    public ObjectWithEightPrimitiveArrays() {
    }

    public boolean[] getBooleanArray() {
        return booleanArray;
    }

    public char[] getCharArray() {
        return charArray;
    }

    public float[] getFloatArray() {
        return floatArray;
    }

    public double[] getDoubleArray() {
        return doubleArray;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public short[] getShortArray() {
        return shortArray;
    }

    public int[] getIntArray() {
        return intArray;
    }

    public long[] getLongArray() {
        return longArray;
    }

    public void setBooleanArray(boolean[] booleanArray) {
        this.booleanArray = booleanArray;
    }

    public void setCharArray(char[] charArray) {
        this.charArray = charArray;
    }

    public void setFloatArray(float[] floatArray) {
        this.floatArray = floatArray;
    }

    public void setDoubleArray(double[] doubleArray) {
        this.doubleArray = doubleArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public void setShortArray(short[] shortArray) {
        this.shortArray = shortArray;
    }

    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }

    public void setLongArray(long[] longArray) {
        this.longArray = longArray;
    }
}
