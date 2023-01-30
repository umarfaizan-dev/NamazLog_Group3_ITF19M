package com.example.classactivity;

public class Person {
    private String Name;
    private String Date;
    private String[] namazCount;
    private boolean[] jamat;

    public Person(String name, String date, String[] namaz,boolean[] jamat) {
        this.Name = name;
        this.Date = date;
        this.namazCount = namaz;
        this.jamat=jamat;
    }

    public boolean[] getJamat() {
        return jamat;
    }

    public void setJamat(boolean[] jamat) {
        this.jamat = jamat;
    }

    public String[] getNamazCount() {
        return namazCount;
    }

    public void setNamazCount(String[] namazCount) {
        this.namazCount = namazCount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
