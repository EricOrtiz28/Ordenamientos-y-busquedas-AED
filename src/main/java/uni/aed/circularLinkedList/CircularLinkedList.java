/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.circularLinkedList;

/**
 *
 * @author AndreP
 */

public class CircularLinkedList {
    Nodo head;
    Nodo ultimo;

    public void addLast(int data){
        Nodo newNodo = new Nodo(data);
        if(ultimo == null){
            head = newNodo;
            ultimo = newNodo;
            head.next = head; // Connecting to itself since only one node.
            head.prev = head; // Connecting to itself since only one node.
        }else{
            newNodo.prev = ultimo;
            newNodo.next = head; // Point to the first node to make it circular
            ultimo.next = newNodo;
            head.prev = newNodo; // Make sure the first node points back to the new node
            ultimo = newNodo;
        }
    }

    public void clear(){
        head = null;
        ultimo = null;
    }

    @Override
    public String toString(){
        if(head == null){
            return "Lista vacía";
        }
        Nodo actual = head;
        String lista = "";

        do {
            if(actual.prev == head && actual == head)
                lista = "null<-"+actual.data+"->"+actual.next.data;
            else if(actual.next == head)
                lista = lista + "||" + actual.prev.data + "<-" + actual.data + "->" + actual.next.data;
            else
                lista = lista + "||" + actual.prev.data + "<-" + actual.data + "->" + actual.next.data;
            
            actual = actual.next;
        } while(actual != head);

        return lista;
    }

    class Nodo {
        int data;
        Nodo next;
        Nodo prev;

        public Nodo(int data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        System.out.println(list.toString());
    }
}
