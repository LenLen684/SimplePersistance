package models;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id, hireYear;
    private String firstName,lastName;

    public Employee(int id, int hireYear, String firstName, String lastName) {
        this.id = id;
        this.hireYear = hireYear;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee Id:"+id +", "+firstName + " " + lastName + ", Hire Year:" + hireYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHireYear() {
        return hireYear;
    }

    public void setHireYear(int hireYear) {
        this.hireYear = hireYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
