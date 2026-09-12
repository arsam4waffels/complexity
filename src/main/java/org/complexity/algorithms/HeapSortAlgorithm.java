package org.complexity.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>HeapSort — the minimalist.</b>
 * <p>Builds a heap, extracts the max one by one.</p>
 * <p>Always O(n log n), always O(1) space. No drama, no extra memory.</p>
 * <p>Not the fastest in practice, but never asks for more than it needs.</p>
 */
public class HeapSortAlgorithm <T extends Comparable<T>> implements SortingAlgorithm<T> {
    @Override
    public List<T> sort(List<T> list) {

        // why bother at this point?
        if (list.size() <= 1) return list;

        // you give me the original, I'll give a sorted copy. deal?
        List<T> copy = new ArrayList<>(list);
        int n = copy.size();

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(copy, n, i);

        for (int i = n - 1; i > 0; i--) {
            T temp = copy.get(0);
            copy.set(0, copy.get(i));
            copy.set(i, temp);
            heapify(copy, i, 0);
        }

        // It's not cheap, it's sorted
        return copy;
    }

    // It operates based on a binary tree.
    // The parent value must be greater than the child value.
    private void heapify(List<T> list, int n, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && list.get(left).compareTo(list.get(largest)) > 0)
            largest = left;

        if (right < n && list.get(right).compareTo(list.get(largest)) > 0)
            largest = right;

        if (largest != i) {

            T temp = list.get(i);

            list.set(i, list.get(largest));
            list.set(largest, temp);
            heapify(list, n, largest);
        }
    }
}
