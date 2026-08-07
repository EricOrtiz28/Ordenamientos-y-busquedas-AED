/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.sorting;

/**
 *
 * @author AndreP
 */
public class Sorting {
    int numSwaps,  numComps; // Counting up swaps and comparissons
    double executionTime;
    public Sorting() {
        this.numSwaps = 0;
        this.numComps = 0;
        this.executionTime = -1; // When not calculated
    }
    
    public int getNumSwaps() {
        return this.numSwaps;
    }
    
    public int getNumComparissons() {
        return this.numComps;
    }
    
    public double getExecutionTime() {
        return this.executionTime;
    }
    
    public Integer[] bubble(Integer[] arr) {

        int n = arr.length;
        long initTime=System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                numComps++;
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
        return arr;
    }
    public Integer[] insertion(Integer[] arr) {
        int aux, k;
        boolean sw = false;
        long initTime=System.nanoTime();

        for (int i = 1; i < arr.length; i++){
            aux = arr[i];
            k = i - 1;
            sw = false;
            while(sw == false && k >= 0){
                numComps+= 3; // 3 
                if (aux < arr[k]) {
                    numSwaps++;
                    arr[k+1] = arr[k];
                    k--;
                }
                else
                    sw = true;
            }
            arr[k+1] = aux;
        }
        long finalTime =System.nanoTime();
        executionTime = finalTime - initTime;
        return arr;
    }
    
    public Integer[] binaryInsertion(Integer[] arr) {
        int aux, p, u, c;
        long initTime=System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            aux = arr[i];
            p = 0;
            u = i - 1;
            while(p <= u) {
                c = (p + u) / 2;
                numComps++;
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
        return arr;
    }
    
    
    public Integer[] selection(Integer[] arr) {
        long initTime=System.nanoTime();
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                numComps++;
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
        
        return arr;
    }

    
    
    public Integer[] quickSort(Integer[] arr) {
        long initTime=System.nanoTime();
        quickSortHelper(arr, 0, arr.length - 1);
        long finalTime =System.nanoTime();
        executionTime = finalTime - initTime;
        return arr;
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
            numComps++;
            if (arr[j] < pivot) {
                i++;
                numSwaps++;
                swap(arr, i, j);
            }
        }

        numSwaps++;
        swap(arr, i + 1, right);
        return i + 1;
    }

    private void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    public Integer[] merge(Integer[] arr) {
        long initTime=System.nanoTime();
        mergeSortHelper(arr, 0, arr.length - 1);
        long finalTime =System.nanoTime();
        executionTime = finalTime - initTime;
        return arr;
    }

    private void mergeSortHelper(Integer[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(Integer[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Integer[] leftArr = new Integer[n1];
        Integer[] rightArr = new Integer[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            numComps++;
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    public Integer[] heap(Integer[] arr) {
        int n = arr.length;
        long initTime=System.nanoTime();

        // Build a max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            numSwaps++;
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }

        long finalTime =System.nanoTime();
        executionTime = finalTime - initTime;
        return arr;
    }

    private void heapify(Integer[] arr, int n, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        
        numComps += 3; // 3 'if' sentences
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != root) {
            numSwaps++;
            swap(arr, root, largest);
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

}
