package org.complexity.model;

public enum Algorithm {
    /*
     * The Avengers of sorting algorithms.
     * Each one has its own superpower — and its own weakness.
     * Choose wisely, or let the ScoringEngine choose for you.
     */
    QUICKSORT("QuickSort", "O(n log n)", "O(log n)"),
    MERGESORT("MergeSort", "O(n log n)", "O(n)"),
    TIMSORT("TimSort", "O(n log n)", "O(n)"),
    HEAPSORT("HeapSort", "O(n log n)", "O(1)"),
    INSERTION_SORT("Insertion Sort", "O(n²)", "O(1)"),
    COUNTING_SORT("Counting Sort", "O(n+k)", "O(k)"),
    RADIX_SORT("Radix Sort", "O(nk)", "O(n+k)");
    private final String displayName;
    private final String timeComplexity;
    private final String spaceComplexity;
    Algorithm(String displayName,
              String timeComplexity,
              String spaceComplexity) {
        this.displayName = displayName;
        this.timeComplexity = timeComplexity;
        this.spaceComplexity = spaceComplexity;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getTimeComplexity() {
        return timeComplexity;
    }
    public String getSpaceComplexity() {
        return spaceComplexity;
    }
}
