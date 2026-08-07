/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.search;

/**
 *
 * @author AndreP
 */
public class SearchObject {
    public final int NOT_FOUND = -1;// Retorna -1 si el elemento no se encuentra en el arreglo

    public int linearSearch(Object[] arr, Object target) {
        for (int i = 0; i < arr.length; i++)
            if (((Comparable) arr[i]).compareTo(target) == 0)
                return i;
        return NOT_FOUND;
    }
    
    
    public int binarySearch(Object[] arr, Object target) {
        int left = 0, 
            right = arr.length - 1,
            mid = (left + right) / 2;
        
        while (left <= right && ((Comparable) arr[mid]).compareTo(target) != 0) {
            
            mid = (left + right) / 2;
            int comparison = ((Comparable) arr[mid]).compareTo(target);
            
            if (comparison == 0)
                return mid;
            if (comparison < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return NOT_FOUND;
    }
    
    
        
}

