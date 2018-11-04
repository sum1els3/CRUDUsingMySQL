package com.example.karl.crudusingmysql;

public class Person {
    public Person(int id, String lName, String fName, String mName)
    {
        this.id = id;
        this.lName = lName;
        this.fName = fName;
        this.mName = mName;
    }

    public int id;
    public String lName;
    public String fName;
    public String mName;

    public String GetName()
    {
        return String.format("%1$s, %2$s %3$s.", lName, fName, mName);
    }

    public int GetID()
    {
        return id;
    }
}
