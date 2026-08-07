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
public class SelectionSort implements Algorithm {
    private int numSwaps;
    private int numComparissons;
    private double executionTime;

    @Override
    public void sort(Integer[] arr) {
        long initTime=System.nanoTime();
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                numComparissons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                numSwaps++;
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
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

