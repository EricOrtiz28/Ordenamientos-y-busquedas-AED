/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.linkedListADT;

/**
 *
 * @author AndreP
 */
public interface IteratorADT <E> {
    public boolean hasNext();
    public E next() throws NoSuchElementException;
    
    
}
