package uni.aed.doublelinkedlist;

public class DoubleLinkedList {
    Nodo head;
    Nodo ultimo;
    
    public void addLast(int data){
        Nodo newNodo=new Nodo(data);
        if(ultimo==null){
            head=newNodo;
            ultimo=newNodo;            
        }else{
            newNodo.prev =ultimo;
            ultimo.next=newNodo;
            ultimo=newNodo;            
        }
    }
    public void clear(){
        head=null;
        ultimo=null;
    }
    public String toString(){
        Nodo actual=head;
        String lista="";
        while(actual!=null){
            if(actual.prev==null)
                lista="null<-"+actual.data+"->"+actual.next.data;
            else if(actual.next==null)
                lista=lista+"||"+actual.prev.data+"<-"+
                        actual.data+"->null";
            else
                lista=lista+"||"+actual.prev.data+"<-"+actual.data+"->"+
                        actual.next.data;
            actual=actual.next;
        }
        return lista;
    }
}
