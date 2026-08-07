/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uni.aed.directory;

import model.Person;

/**
 *
 * @author AndreP
 */
public interface Directory {
    public void add(Person newPerson);
    public Person[] sort(int attribute, String algorithm);
    //public void Search();
}
