/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.linkedListADT;

/**
 *
 * @author AndreP
 */
public class Node <E>{
    private E data;
//    private Node prev;
    private Node next;
    public Node (E data) {
        this.data = data;
        this.next = null;
//        this.prev = null;
    }

    public E getData() {
        return data;
    }

//    public Node getPrev() {
//        return prev;
//    }

    public Node getNext() {
        return next;
    }

    public void setData(E data) {
        this.data = data;
    }

//    public void setPrev(Node prev) {
//        this.prev = prev;
//    }

    public void setNext(Node next) {
        this.next = next;
    }
    
    
    
}
