/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author AndreP
 */
public class Person implements Comparable {
    public static final int NAME = 0;
    public static final int AGE = 1;
    
    public static final int LESS = -1;
    public static final int EQUAL = 0;
    public static final int MORE = 1;

    public static int compareAttribute;
    
    static{
        compareAttribute=NAME;
    }
    
    
    
    private String name;
    private int age;
    private char sex;

    public Person() {
        this("Undefined",0,'U');
    }

    public Person(String name, int age, char sex) {
        this.name = name;
        this.age  = age;
        this.sex  = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return this.name + " | "
                + this.age + " | "
                + this.sex;
    }
    
    public void setCompareAttribute(int attribute){
        compareAttribute = attribute;
    }
    
    public int compareTo(Person person){
        return compareTo(person, compareAttribute);
    }

    public int compareTo(Person person, int attribute) {
        int comparissonResult;
        if(attribute == AGE){
            int p2age = person.getAge();
            
            if(this.age < p2age )
                comparissonResult = LESS;
            else if(this.age == p2age)
                comparissonResult = EQUAL;
            else
                comparissonResult = MORE;
        }
        else{
            String p2name = person.getName();
            comparissonResult = this.name.compareTo(p2name);
        }
        return comparissonResult;
    }
       
    @Override
    public int compareTo(Object o) {
        return compareTo((Person) o, compareAttribute);
    }
}