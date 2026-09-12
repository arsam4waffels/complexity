package org.complexity.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * <h5>TimSort — the smart one.</h5>
 * <p>A hybrid of MergeSort and InsertionSort. Java's own sorting algorithm under the hood.</p>
 * <p>Finds naturally sorted runs in the array and merges them together.</p>
 * <p>Loves nearly-sorted arrays like cats love warm laptops.</p>
 * <p>Always O(n log n), stable, and battle-tested.</p>
 */
public class TimSortAlgorithm<T extends Comparable<T>> implements SortingAlgorithm<T> {

    // It divides the array into 32 segments and sorts them using Insertion Sort.
    private static final int RUN = 32;

    @Override
    public List<T> sort(List<T> list) {
        if (list.size() <= 1) return list;
        List<T> copy = new ArrayList<>(list);
        int n = copy.size();

        for (int i = 0; i < n; i += RUN)
            insertionSort(copy, i, Math.min(i + RUN - 1, n - 1));

        // We use merge sort algorithm for runs
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);
                if (mid < right)
                    merge(copy, left, mid, right);
            }
        }

        return copy;
    }

    private void insertionSort(List<T> list, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            T key = list.get(i);
            int j = i - 1;
            while (j >= left && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    private void merge(List<T> list, int left, int mid, int right) {
        List<T> leftList = new ArrayList<>(list.subList(left, mid + 1));
        List<T> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).compareTo(rightList.get(j)) <= 0)
                list.set(k++, leftList.get(i++));
            else
                list.set(k++, rightList.get(j++));
        }

        while (i < leftList.size()) list.set(k++, leftList.get(i++));
        while (j < rightList.size()) list.set(k++, rightList.get(j++));
    }
}
