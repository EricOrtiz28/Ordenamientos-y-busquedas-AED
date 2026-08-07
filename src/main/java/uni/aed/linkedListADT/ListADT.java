/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uni.aed.linkedListADT;

/**
 *
 * @author AndreP
 */
interface ListADT<E>{
    public void add(E element);
    public void add(int index, E element) 
        throws IndexOutOfBoundsException;
    public void clear();
    public boolean contain(E element);
    public E get (int index) throws IndexOutOfBoundsException;
    public int indexOf(E element);
    public boolean isEmpty();
    public E delete(int index) throws IndexOutOfBoundsException;
    public boolean delete(E element);
    public E modify(int index, E element);
    public int size();
}
