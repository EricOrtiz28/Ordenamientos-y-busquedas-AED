
package uni.aed.sorting;

import uni.aed.sortingclasses.BinaryInsertionSort;
import uni.aed.sortingclasses.BubbleSort;
import uni.aed.sortingclasses.InsertionSort;
import uni.aed.sortingclasses.SelectionSort;

/**
 *
 * @author AndreP
 */
public enum Algorithms {
    BUBBLE("Bubble", "O(n^2)", BubbleSort.class, 
           "(n-1)^2", "Max (n-1)^2", "O(n^2)"),
    
    INSERTION("Insertion", "O(n^2)", InsertionSort.class, 
              "(n^2)/4", "(n^2)/2", "O(n^2)"),
    
    BINARY_INSERTION("Binary Insertion", "O(n * log^2(n))", BinaryInsertionSort.class, 
                     "n * log2(n)", "(n^2)/2", "O(n * log^2(n))"),
    
    SELECTION("Selection", "O(n^2)", SelectionSort.class, 
              "(n^2)/2", "(n-1)", "O(n^2)"),
    
    QUICKSORT("QuickSort", "O(n * log(n))", QuickSort.class, 
              "O(n * log(n))", "< (n-1)^2", "O(n^2)");

    private final String name;
    private final String complexity;
    private final Class<? extends Algorithm> algoClass;
    private final String numComparissons;
    private final String numSwaps;
    private final String worstCaseComplexity;

    Algorithms(String name, String complexity, Class<? extends Algorithm> algoClass, 
               String numComparissons, String numSwaps, String worstCaseComplexity) {
        this.name = name;
        this.complexity = complexity;
        this.algoClass = algoClass;
        this.numComparissons = numComparissons;
        this.numSwaps = numSwaps;
        this.worstCaseComplexity = worstCaseComplexity;
    }

    public String getName() {
        return name;
    }

    public String getComplexity() {
        return complexity;
    }

    public Class<? extends Algorithm> getAlgoClass() {
        return algoClass;
    }

    public String numComparissons() {
        return numComparissons;
    }

    public String worstCaseComplexity() {
        return worstCaseComplexity;
    }
}
