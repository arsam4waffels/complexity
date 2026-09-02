package org.complexity;

import org.complexity.scoring.AlgorithmScore;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Complexity<Integer> c = new Complexity.Builder<>(
                List.of(9,3,7,1,8,2,6,4,5,0,11,15,13,12,14,10,16,20,18,17,19))
                .speedOverMemory(true)
                .needsStable(false)
                .memoryConstrained(false)
                .build();

        AlgorithmScore result = c.analyze();
        System.out.println(result.getAlgorithm().getDisplayName());
        System.out.println(result.getReason());
    }
}