package org.complexity.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h5>CountingSort — the cheat code.</h5>
 * <p>No comparisons. Just counts how many times each number appears,
 * then rebuilds the array from those counts.</p>
 * <p>O(n+k) time — yes, really. Integers only though.</p>
 * <p>Try it on Strings and it walks out.</p>
 */
public class CountingSortAlgorithm implements SortingAlgorithm<Integer> {
    @Override
    public List<Integer> sort(List<Integer> list) {

        // I'm assuming either you don't know how many elements your array has,
        // or you're just bored.
        if (list.size() == 1) return list;

        int min = Collections.min(list);
        int max = Collections.max(list);

        int[] count = new int[max - min + 1];

        for (int num : list)
            count[num - min]++;

        // No comparing, just straight up counting
        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < count.length; i++)
            for (int j = 0; j < count[i]; j++)
                sorted.add(i + min);

        return sorted;
    }
}
