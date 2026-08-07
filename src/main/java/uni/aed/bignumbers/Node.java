/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.bignumbers;

/**
 *
 * @author AndreP
 */
class Node {
    /*
    Nodo que almacena un número de dígitos máximo en un bloque
    */
    public static final int MAX_DIGITS = 3;
    public static final short MAX_VALUE = 1000; // Rango de valores de un bloque
    public short value;
    public Node next;
    
    // OJO: En el libro de Wu se definieron constructores privados, pero 
    // esto no permitía instanciar la clase nodo en BigInt
    public Node() {
        this("0");
    }
    public Node(String str) {
        this(Short.parseShort(str));
    }
    public Node(short val) {
        value = val;
        next = null;
    }
}
