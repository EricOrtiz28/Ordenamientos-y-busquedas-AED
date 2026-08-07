/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.linkedListADT;

/**
 *
 * @author AndreP
 */
public class LinkedListADT <E> implements ListADT <E> {
    private Node head;
    private Node tail;
    private int count;
    
    public LinkedListADT(){
        clear();
        
    }
    
    @Override
    public void add(E element) {
        Node newNode = new Node(element);
        if(count == 0)
            head = tail = newNode;
        else{
            tail.setNext(newNode);
            tail = newNode;
        }
        count++;
        
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        checkBoundaries(index);
        Node apt = head;
        Node newNode = new Node(element);
        if(index==0){
            newNode.setNext(head);
            head = newNode;
            count++;
        }
        else{
            for (int i = 0; i<index ; i++){
//                apt=apt . getNext ( ) ;
//                newNodo . setNext (
//                apt . getNext ( ) )
//                next :
//                apt . setNext (
//                newNodo) •
//                next :
               }
        }
        
//        if(index==count)
            
  
            
    }
    
    private void checkBoundaries(int index){
        if ( index < 0)
            throw new IndexOutOfBoundsException ("Index is invalid (negative)");
        else if(index > count){
            throw new IndexOutOfBoundsException ("Index out of boundaries (higher than the size)");
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public boolean contain(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int indexOf(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E modify(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int size() {
        return count;
    }
    
    @Override
    public String toString(){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
