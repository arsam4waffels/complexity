package org.complexity.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * <h5>MergeSort — the reliable one.</h5>
 * <p>Divides the list in half, sorts each half, then merges them back together.
 * Always O(n log n). Never complains. Just works.</p>
 *
 * <p>Note: this is a sorting implementation — not the inversion-counting
 * version in ArrayAnalyzer. same idea, different purpose.</p>
 */
public class MergeSortAlgorithm<T extends Comparable<T>> implements SortingAlgorithm<T> {

    @Override
    public List<T> sort(List<T> list) {

        // If it has one member, there is no need to examine it.
        if (list.size() == 1) return list;

        // We create a copy of the array and pass it to the sorting algorithm.
        List<T> copy = new ArrayList<>(list);
        mergeSort(copy, 0, copy.size() - 1);

        // return the sorted array (not the real one)
        return copy;
    }

    private void mergeSort(
            List<T> list,
            int left,
            int right) {

        if (left < right) {

            int mid = (left + right) / 2;

            // one for left, one for right
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            // then we merge them
            merge(list, left, mid, right);
        }
    }
    /**
     * <p>The compiler is telling me I've already written this code
     * somewhere else. Yeah, I know! I'm not crazy enough to go
     * around rewriting code for no reason.</p>
     * @see org.complexity.analyzer.ArrayAnalyzer
     */
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
