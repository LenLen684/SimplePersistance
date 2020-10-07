package models;

public class LargeEmployee extends Employee {

    private byte[] someData;

    public LargeEmployee(int id, int hireYear, String firstName, String lastName, byte[] someData) {
        super(id, hireYear, firstName, lastName);
        this.someData = someData;
    }

    public byte[] getSomeData() {
        return someData;
    }

    public void setSomeData(byte[] someData) {
        this.someData = someData;
    }


}