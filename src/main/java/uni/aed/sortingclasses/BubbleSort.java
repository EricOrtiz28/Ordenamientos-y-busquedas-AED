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

public class BubbleSort implements Algorithm {
    private int numSwaps;
    private int numComparissons;
    private double executionTime;

    @Override
    public void sort(Integer[] arr) {
        int n = arr.length;
        long initTime=System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                numComparissons++;
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    numSwaps++;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
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
