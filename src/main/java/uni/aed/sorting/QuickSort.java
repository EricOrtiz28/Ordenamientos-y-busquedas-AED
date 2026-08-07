/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.sorting;

/**
 *
 * @author AndreP
 */
public class QuickSort implements Algorithm {
    private int numSwaps;
    private int numComparissons;
    private double executionTime;
    
    @Override
    public void sort(Integer[] arr) {
        long initTime=System.nanoTime();
        quickSortHelper(arr, 0, arr.length - 1);
        long finalTime =System.nanoTime();
        executionTime = finalTime - initTime;
    }

    private void quickSortHelper(Integer[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSortHelper(arr, left, pivotIndex - 1);
            quickSortHelper(arr, pivotIndex + 1, right);
        }
    }

    private int partition(Integer[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            numComparissons++;
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, right);
        return i + 1;
    }
    
    private void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        numSwaps++;
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
