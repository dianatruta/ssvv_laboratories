package model;

public class Entry {

    private int value;
    private String typeEntry;// cost or income
    private int idMember;

    public Entry(String typeEntry, int value, int idMember) {
        this.typeEntry = typeEntry;
        this.value = value;
        this.idMember = idMember;
    }

    public void setType(String newType) {
        typeEntry = newType;
    }

    public String getType() {
        return typeEntry;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }

    public int getValue() {
        return value;
    }

    public void setMember(int newMember) {
        this.idMember = newMember;
    }

    public int getIdMember() {
        return idMember;
    }

    public String toString() {
        return String.format("%s;%d;%d", this.typeEntry, this.value, this.idMember);
    }

}
