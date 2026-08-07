/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.bicycle;

/**
 *
 * @author AndreP
 */
public class BNodo {
    private Bicicleta elemento;
    private BNodo siguiente;
//    private BNodo anterior;
    
    public BNodo (){
        this (null, null);
    }
    public BNodo (Bicicleta dato, BNodo nodo){
        estableceElemento(dato);
        estableceSiguiente(nodo);
        //estableceAnterior(nodo);
    }
        
        
    public Bicicleta obtenElemento( ){
        return elemento;
    }
        
    public BNodo obtenSiguiente ( ){
        return siguiente;
    }
//    public BNodo obtenAnterior ( ){
//        return anterior;
//    }
       
    public void estableceElemento (Bicicleta dato){
        elemento = dato;
    }
    public void estableceSiguiente (BNodo nodo){
        siguiente = nodo;
    }
//    public void estableceAnterior (BNodo nodo){
//        anterior = nodo;
//    } 
        
}
