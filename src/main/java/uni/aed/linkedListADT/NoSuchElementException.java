/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.linkedListADT;

/**
 *
 * @author AndreP
 */
public class NoSuchElementException extends RuntimeException{
    public NoSuchElementException (String message){
        super("message");
    }
    public NoSuchElementException(){
        this("Elemento solicitado no existe en la estructura");
    }
}
