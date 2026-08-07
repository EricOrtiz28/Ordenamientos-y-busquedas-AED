/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.sorting;

/**
 *
 * @author AndreP
 */
public class SortingFactory {

    public static Algorithm getAlgorithm(Algorithms algoType) {
        try {
            return algoType.getAlgoClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to create algorithm instance", e);
        }
        //ANTES TENIA ESTO:
//        switch (algoType) {
//            case BUBBLE:
//                return new BubbleSort();
//            case INSERTION:
//                return new InsertionSort();
//            case BINARY_INSERTION:
//                return new BinaryInsertionSort();
//            case SELECTION:
//                return new SelectionSort();
//            case QUICKSORT:
//                return new QuickSort();
//            // ... Add other sorting algorithms here
//            default:
//                throw new IllegalArgumentException("Invalid sorting algorithm type");
//        }
    }
}

