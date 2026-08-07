/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.directory;

import model.Person;
import uni.aed.sortingclasses.SortObjectPerson;

/**
 *
 * @author AndreP
 * CODIGO: 20222189G
 */
public class DirectoryV1 implements Directory{
    private static final int DEFAULT_SIZE = 25;
    
    private Person[] entry;     // Data entry
    private int count;

    public DirectoryV1() {
        this(DEFAULT_SIZE); // Calls the constructor with the default size
    }

    public DirectoryV1(int size) {
        count = 0;
        if (size <= 0){
            throw new IllegalArgumentException("Size has to be positive.");
        }
        entry = new Person[size];
    }
    
    public Person[] getEntry() {
        return entry;
    }
    
    @Override
    public void add(Person newPerson) {
        if (count == entry.length)
            enlarge();
            
        entry[count] = newPerson;
        count++;
    }

    @Override
    public Person[] sort(int attribute, String algorithm) {
        if (!(attribute == Person.NAME || attribute == Person.AGE))
            throw new IllegalArgumentException();
        Person[] sortedList = new Person[count];
        for (int i = 0; i < count; i++)
            sortedList[i] = entry[i];
        SortObjectPerson o = new SortObjectPerson();
        switch(algorithm.toUpperCase()){
            case "BUBBLE"->{
                o.Bubble(entry, attribute);
            }
        }
        return sortedList;
    }
    
    private void enlarge() {
        int newLength = (int) (1.5 * entry.length);
        Person[] temp = new Person[newLength];
        for(int i = 0; i < entry.length; i++)
            temp[i] = entry[i];
        entry = temp;
    }

    
    
}
