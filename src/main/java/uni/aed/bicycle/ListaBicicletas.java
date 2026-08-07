/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.bicycle;

/**
 *
 * @author AndreP
 */

public class ListaBicicletas {
    private BNodo head;
    
    public void addFirst(Bicicleta bicicleta){
        BNodo newBNodo=new BNodo(bicicleta, head);
        head=newBNodo;
    }
    public static void main(String[] args) {
        ListaBicicletas lista = new ListaBicicletas();
        
        // Debugging:
        Bicicleta uno = new Bicicleta("Andre");
        Bicicleta dos = new Bicicleta("Mitch");
        lista.addLast(uno);
        lista.addLast(dos);
        System.out.println(lista);
        lista.remove("Andre");
        System.out.println(lista);
    }
            
    
    public void addLast(Bicicleta bicicleta){
        BNodo newBNodo=new BNodo(bicicleta, null);
        if(head==null){
            head=newBNodo;
            return;
        }
        BNodo current =head;
        while(current.obtenSiguiente()!=null)
            current=current.obtenSiguiente();        
        current.estableceSiguiente(newBNodo);
    }
    
    public void remove(String nomPropietario){
        if(head==null)
            return;
        //si el elemento a eliminar es el primer nodo
        if(head.obtenElemento().getNomPropietario().equals(nomPropietario)){
        //if( ((Comparable) head.obtenElemento().getNomPropietario()).compareTo(nomPropietario) != 0){
            head=head.obtenSiguiente();
            return;
        }
        //si el nodo a eliminar no es el primer nodo
        BNodo current=head;
        while(current.obtenSiguiente()!=null && !current.obtenSiguiente().obtenElemento().getNomPropietario().equals(nomPropietario))
            current=current.obtenSiguiente();
        
        if(current.obtenSiguiente()!=null)
            current.estableceSiguiente(current.obtenSiguiente().obtenSiguiente());
        
    }
    
    public void clear(){
        head=null;     
    }
    
    public String toString(){
        String lista="";
        BNodo current =head;
        while(current!=null){
            if(lista.length()==0)
                lista=current.obtenElemento().getNomPropietario();
            else
                lista=lista +"->"+current.obtenElemento().getNomPropietario();
            current=current.obtenSiguiente();
        }
        return lista;        
    }
}

