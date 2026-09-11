package org.complexity.algorithms;

import java.util.ArrayList;
import java.util.List;

public class QuickSortAlgorithm<T extends Comparable<T>> implements SortingAlgorithm<T> {
    @Override
    public List<T> sort(List<T> list) {

        // If it has one member, there is no need to examine it.
        if (list.size() == 1) return list;

        // We create a copy of the array and pass it to the sorting algorithm.
        List<T> copy = new ArrayList<>(list);
        quickSort(copy, 0, copy.size() - 1);

        // return the sorted array (not the real one)
        return copy;
    }
    private void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }
    private int partition(List<T> list, int low, int high) {
        T pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++)
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }

        // They pass the items from hand to one another, Teamwork!
        T temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }
}
