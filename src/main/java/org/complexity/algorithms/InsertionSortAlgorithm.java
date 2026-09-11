package org.complexity.algorithms;

import java.util.ArrayList;
import java.util.List;

public class InsertionSortAlgorithm<T extends Comparable<T>> implements SortingAlgorithm<T> {

    @Override
    public List<T> sort(List<T> list) {

        // If it has one member, there is no need to examine it.
        if (list.size() == 1) return list;

        // We create a copy of the array and pass it to the sorting algorithm.
        List<T> copy = new ArrayList<>(list);

        for (int i = 1; i < copy.size(); i++) {

            T key = copy.get(i);

            int j = i - 1;
            while (j >= 0 && copy.get(j).compareTo(key) > 0) {
                copy.set(j + 1, copy.get(j));
                j--;
            }

            copy.set(j + 1, key);
        }

        // copy cat!
        return copy;
    }
}
