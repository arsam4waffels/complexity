package org.complexity;

import org.complexity.model.Algorithm;
import org.complexity.scoring.AlgorithmScore;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Complexity<Integer> c = new Complexity.Builder<>(
                List.of(3, 1, 4, 1, 5, 9, 2, 6))
                .speedOverMemory(true)
                .build();

        // AlgorithmScore result = c.analyze();
        List<Integer> sorted = c.sort(Algorithm.MERGESORT);
        System.out.println(sorted);

        // just testing if other algorithms work
        // probably should write a hole test for each one of them in the future
        // cant promise you that.
        System.out.println(c.sort(Algorithm.QUICKSORT));
        System.out.println(c.sort(Algorithm.TIMSORT));
        System.out.println(c.sort(Algorithm.HEAPSORT));
        System.out.println(c.sort(Algorithm.INSERTION_SORT));
        System.out.println(c.sort(Algorithm.COUNTING_SORT));
        System.out.println(c.sort(Algorithm.RADIX_SORT));
    }
}