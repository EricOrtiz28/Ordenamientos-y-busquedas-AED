/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.search;

/**
 *
 * @author AndreP
 */
public class Search {
    
    public final int NOT_FOUND = -1;// Retorna -1 si el elemento no se encuentra en el arreglo
    
    public int Linear(Integer [] X, int valor) {
        int loc = 0;
        while (loc < X.length && X[loc] != valor) {
            loc ++;
        }
        
        if (loc == X.length)
            return NOT_FOUND;
        else
            return loc;
    }
    
    
    public int Binary(Integer[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (arr[m] == x) {
                return m;
            }

            if (arr[m] < x) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return NOT_FOUND; 
    }
    
}