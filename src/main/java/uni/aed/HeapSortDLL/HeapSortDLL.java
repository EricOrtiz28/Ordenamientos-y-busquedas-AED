/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.HeapSortDLL;

/**
 *
 * @author AndreP
 */
public class HeapSortDLL{

    // Método para remover el último nodo (hoja) del árbol binario
    // y devolver su valor, ajustando los punteros del árbol.
    public int removeLastElement(DNodo root, DNodo parent) {
        if (root == null) return -1;

        // Si es una hoja, lo eliminamos y retornamos su valor
        if (root.next == null && root.prev == null) {
            int data = root.data;
            
            // Desvincular el nodo hoja de su padre
            if (parent.next == root) parent.next = null;
            if (parent.prev == root) parent.prev = null;
            
            return data;
        }
        
        // Buscar y remover recursivamente el último nodo
        // Si tiene hijo derecho, vamos por ese camino, sino por el izquierdo
        return (root.next != null) ? removeLastElement(root.next, root) : removeLastElement(root.prev, root);
    }

    // Método para ajustar el árbol asegurando que sea un MaxHeap.
    public void heapify(DNodo root) {
        if (root == null) return;

        DNodo largest = root;

        if (root.next != null && root.next.data > largest.data) 
            largest = root.next;

        if (root.prev != null && root.prev.data > largest.data) 
            largest = root.prev;

        if (largest != root) {
            int temp = root.data;
            root.data = largest.data;
            largest.data = temp;
            
            heapify(largest);
        }
    }

    // Método para construir el MaxHeap usando el método heapify.
    public void heap_sort(DNodo root) {
        if (root == null) return;
        
        heap_sort(root.next);
        heap_sort(root.prev);
        
        heapify(root);
    }

    // Método principal para realizar el HeapSort y retornar un array ordenado.
    public Integer[] main_heap(DNodo root, int length) {
        heap_sort(root);

        Integer[] orderedArray = new Integer[length];
        DNodo parent = new DNodo(0); // Nodo ficticio para actuar como padre del root
        parent.next = root;
        
        // Extracción del elemento máximo y reorganización del heap
        for (int i = length - 1; i >= 0; i--) {
            orderedArray[i] = root.data;
            root.data = removeLastElement(root, parent);
            heapify(root);
        }

        return orderedArray;
    }
    
}


