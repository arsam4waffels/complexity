package org.complexity.analyzer;

import org.complexity.model.DataProfile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/*
 * The detective of this operation.
 * Give it a list, it tells you everything about it —
 * how big it is, how messy it is, and how boring (duplicate-filled) it is.
 *
 * T extends Comparable<T> — because we refuse to sort things
 * that can't even decide which one of them is greater lol
 */
public class ArrayAnalyzer<T extends Comparable<T>> {
    public DataProfile analyze(List<T> list) {
        int size = list.size();
        double sortedness = calculateSortedness(list);
        double uniqueness = calculateUniqueness(list);
        boolean isInteger = !list.isEmpty() && list.get(0) instanceof Integer;
        return new DataProfile(size,
                sortedness,
                uniqueness,
                isInteger
        );
    }
    /*
     * Counts inversions to figure out how chaotic the array is.
     *
     * An inversion is when a bigger element shows up before a smaller one —
     * basically elements that didn't get the memo about being sorted.
     *
     * 0 inversions = perfectly sorted (rare and beautiful)
     * max inversions = completely reversed (chaotic evil)
     *
     * O(n²) — works great for small arrays.
     * For large arrays, future-me will fix this with MergeSort. (soon™ but don't get your hopes up)
     */
    private double calculateSortedness(List<T> list) {
        int n = list.size();
        if (n <= 1) return 1.0;
        long inversions = 0;
        long maxInversions = (long) n * (n - 1) / 2;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    inversions++;
                }
            }
        }
        return 1.0 - (double) inversions / maxInversions;
    }
    /*
     * Measures how unique the elements are.
     * Dumps everything into a HashSet — duplicates vanish like they never existed.
     * Then compares what survived to what we started with.
     *
     * Example :
     * 1.0 = everyone is unique and special :D
     * 0.2 = this array has serious commitment issues with the same values :O
     */
    private double calculateUniqueness(List<T> list) {
        // an empty array is perfectly unique. philosophically speaking.
        if (list.isEmpty()) return 1.0;
        Set<T> unique = new HashSet<>(list);
        return (double) unique.size() / list.size();
    }
}
