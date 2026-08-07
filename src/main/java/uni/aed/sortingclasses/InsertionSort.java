/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.sortingclasses;
import uni.aed.sorting.Algorithm;

/**
 *
 * @author AndreP
 */
public class InsertionSort implements Algorithm {
    private int numSwaps;
    private int numComparissons;
    private double executionTime;

    @Override
    public void sort(Integer[] arr) {
        int aux, k;
        boolean sw = false;
        long initTime=System.nanoTime();

        for (int i = 1; i < arr.length; i++){
            aux = arr[i];
            k = i - 1;
            sw = false;
            while(sw == false && k >= 0){
                numComparissons+= 3; // 3 
                if (aux < arr[k]) {
                    numSwaps++;
                    arr[k+1] = arr[k];
                    k--;
                } else {
                    sw = true;
                }
            }
            arr[k+1] = aux;
        }
        long finalTime =System.nanoTime();
        executionTime = finalTime - initTime;
    }

    @Override
    public int getNumSwaps() {
        return numSwaps;
    }

    @Override
    public int getNumComparissons() {
        return numComparissons;
    }

    @Override
    public double getExecutionTime() {
        return executionTime;
    }
    
}
