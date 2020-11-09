package com.peoplefile.json;

public class PeopleInformationResponse 
{
    String name;
    String age;
    String dateToBirth;
    String poem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDateToBirth() {
        return dateToBirth;
    }

    public void setDateToBirth(String dateToBirth) {
        this.dateToBirth = dateToBirth;
    }

    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    @Override
    public String toString() {
        return "PeopleInformationResponse{" + "name=" + name + ", age=" + age + ", dateToBirth=" + dateToBirth + ", poem=" + poem + '}';
    }
    
    
}
