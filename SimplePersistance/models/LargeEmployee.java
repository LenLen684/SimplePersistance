package models;

public class LargeEmployee extends Employee {

    private byte[] someData;

    public LargeEmployee(int id, String firstName, String lastName, int hireYear, byte[] someData) {
        super(id,  firstName, lastName, hireYear);
        this.someData = someData;
    }

    public byte[] getSomeData() {
        return someData;
    }

    public void setSomeData(byte[] someData) {
        this.someData = someData;
    }


}