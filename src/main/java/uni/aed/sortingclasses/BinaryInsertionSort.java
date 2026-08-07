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
public class BinaryInsertionSort implements Algorithm {
    private int numSwaps;
    private int numComparissons;
    private double executionTime;

    @Override
    public void sort(Integer[] arr) {
        int aux, p, u, c;
        long initTime=System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            aux = arr[i];
            p = 0;
            u = i - 1;
            while(p <= u) {
                c = (p + u) / 2;
                numComparissons++;
                if (aux < arr[c])
                    u = c - 1;
                else
                    p = c + 1;
            }
            for (int k = i-1; k >= p; k--)
                arr[k+1] = arr[k];
            numSwaps += i - p;
            arr[p] = aux;
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
