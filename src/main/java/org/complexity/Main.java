package org.complexity;

import org.complexity.model.Algorithm;
import org.complexity.scoring.AlgorithmScore;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Complexity c = new Complexity.Builder<>(
                List.of(3, 1, 4, 1, 5, 9, 2, 6))
                .speedOverMemory(true)
                .build();

        // AlgorithmScore result = c.analyze();
        List<Integer> sorted = c.sort(Algorithm.MERGESORT);
        System.out.println(sorted);
    }
}