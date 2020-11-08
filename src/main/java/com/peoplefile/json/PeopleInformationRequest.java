package com.peoplefile.json;

import java.util.Date;

public class PeopleInformationRequest 
{
    String name;
    String dateOfBirth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "PeopleInformationRequest{" + "name=" + name + ", dateOfBirth=" + dateOfBirth + '}';
    }
    
    
}
