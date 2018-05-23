package ru.otus.homework08.model;

public class ObjectWithEightBasicTypes {
    private boolean booleanField;
    private char charType;
    private float floatType;
    private double doubleType;

    private byte byteType;
    private short shortType;
    private int intType;
    private long longType;

    public ObjectWithEightBasicTypes() {
    }

    public ObjectWithEightBasicTypes(boolean booleanField, char charType, float floatType, double doubleType, byte byteType, short shortType, int intType, long longType) {
        this.booleanField = booleanField;
        this.charType = charType;
        this.floatType = floatType;
        this.doubleType = doubleType;
        this.byteType = byteType;
        this.shortType = shortType;
        this.intType = intType;
        this.longType = longType;
    }

    public boolean isBooleanField() {
        return booleanField;
    }

    public char getCharType() {
        return charType;
    }

    public float getFloatType() {
        return floatType;
    }

    public double getDoubleType() {
        return doubleType;
    }

    public byte getByteType() {
        return byteType;
    }

    public short getShortType() {
        return shortType;
    }

    public int getIntType() {
        return intType;
    }

    public long getLongType() {
        return longType;
    }

    public void setBooleanField(boolean booleanField) {
        this.booleanField = booleanField;
    }

    public void setCharType(char charType) {
        this.charType = charType;
    }

    public void setFloatType(float floatType) {
        this.floatType = floatType;
    }

    public void setDoubleType(double doubleType) {
        this.doubleType = doubleType;
    }

    public void setByteType(byte byteType) {
        this.byteType = byteType;
    }

    public void setShortType(short shortType) {
        this.shortType = shortType;
    }

    public void setIntType(int intType) {
        this.intType = intType;
    }

    public void setLongType(long longType) {
        this.longType = longType;
    }
}
