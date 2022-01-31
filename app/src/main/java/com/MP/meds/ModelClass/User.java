package com.MP.meds.ModelClass;

public class User {
    String Name;
    String Company;

    public User() {

    }

    public User(String name, String company) {
        Name = name;
        Company = company;
    }
    public User(String name)
    {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }
}
