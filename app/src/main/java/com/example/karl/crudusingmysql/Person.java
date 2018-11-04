package com.example.karl.crudusingmysql;

public class Person {
    public Person(String lName, String fName, String mName)
    {
        this.lName = lName;
        this.fName = fName;
        this.mName = mName;
    }

    public String lName;
    public String fName;
    public String mName;

    public String GetName()
    {
        return String.format("%1$s, %2$s %3$s.", lName, fName, mName);
    }
}
