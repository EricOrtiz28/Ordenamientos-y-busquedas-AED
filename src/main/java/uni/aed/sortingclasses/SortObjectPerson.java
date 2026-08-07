/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.sortingclasses;

import model.Person;

/**
 *
 * @author AndreP
 */
public class SortObjectPerson {
    public Person[] Bubble(Person[] arr, int attribute) {
        if (!(attribute == Person.NAME || attribute == Person.AGE))
            throw new IllegalArgumentException();
        
        Person p1, p2, aux;
        int  comparissonResult;
        arr[0].setCompareAttribute(attribute);
        for(int i = 0; i < arr.length - 1; i++)
            for(int j = 0; j < arr.length - 1; j++){
                p1 = arr[j];
                p2 = arr[j+1];
                comparissonResult = p1.compareTo(p2);
                if(comparissonResult > 0){
                    aux = p1;
                    p1 = p2;
                    p2 = aux;
                }
            }
        return arr;
    }
}
