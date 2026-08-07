/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package uni.aed.sorting;

/**
 *
 * @author AndreP
 */
public interface Algorithm {
    void sort(Integer[] arr);
    int getNumSwaps();
    int getNumComparissons();
    double getExecutionTime();
}

