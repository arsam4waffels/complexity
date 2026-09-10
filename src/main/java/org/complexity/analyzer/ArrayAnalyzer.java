package org.complexity.analyzer;

import org.complexity.model.DataProfile;

import java.util.ArrayList;
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
     * Uses MergeSort under the hood. O(n log n). future-me delivered. eventually.
     */
    private double calculateSortedness(List<T> list) {
        int n = list.size();
        if (n <= 1) return 1.0;

        List<T> copy = new ArrayList<>(list);
        long inversions = mergeSort(copy, 0, n - 1);
        long maxInversions = (long) n * (n - 1) / 2;

        return 1.0 - (double) inversions / maxInversions;
    }
    /*
     * Counts inversions using MergeSort — O(n log n) instead of the naive O(n²).
     * An inversion is a pair (i, j) where i < j but list[i] > list[j].
     * Every time an element from the right half is placed before elements
     * from the left half during merge, those are inversions — and we count them for free.
     */
    private long mergeSort(List<T> list, int left, int right) {
        long inversions = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            inversions += mergeSort(list, left, mid);
            inversions += mergeSort(list, mid + 1, right);
            inversions += merge(list, left, mid, right);
        }
        return inversions;
    }
    private long merge(List<T> list, int left, int mid, int right) {
        long inversions = 0;

        List<T> leftList = new ArrayList<>(list.subList(left, mid + 1));
        List<T> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).compareTo(rightList.get(j)) <= 0) {
                list.set(k++, leftList.get(i++));
            } else {
                inversions += leftList.size() - i;
                list.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) list.set(k++, leftList.get(i++));
        while (j < rightList.size()) list.set(k++, rightList.get(j++));

        return inversions;
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
