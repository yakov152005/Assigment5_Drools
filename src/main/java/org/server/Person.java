package org.server;

public class Person {
    private String name;
    private int age;
    private boolean isAdult;
    private boolean isName;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        this.isAdult = false;
        this.isName = false;
    }

    public boolean getIsName() {return isName;}
    public void setIsName(boolean isName) {this.isName = isName;}
    public String getName() {return name; }
    public int getAge() { return age; }
    public boolean isAdult() { return isAdult; }
    public void setAdult(boolean isAdult) { this.isAdult = isAdult; }
}
