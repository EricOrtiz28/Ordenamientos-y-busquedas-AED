/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.HeapSortDLL;

/**
 *
 * @author AndreP
 */

public class DNodo {
    int data;
    public DNodo prev;
    public DNodo next;
    
    public DNodo(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public int getData() {
        return data;
    }

    public DNodo getPrev() {
        return prev;
    }

    public DNodo getNext() {
        return next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setPrev(DNodo prev) {
        this.prev = prev;
    }

    public void setNext(DNodo next) {
        this.next = next;
    }
    
}
