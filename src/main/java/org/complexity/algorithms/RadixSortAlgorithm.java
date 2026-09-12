package org.complexity.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RadixSortAlgorithm implements SortingAlgorithm<Integer>{

    @Override
    public List<Integer> sort(List<Integer> list) {
        if (list.size() <= 1) return list;

        int max = Collections.max(list);

        List<Integer> sorted = new ArrayList<>(list);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingByDigit(sorted, exp);

        return sorted;
    }
    private void countingByDigit(List<Integer> list, int exp) {

        int n = list.size();

        List<Integer> output = new ArrayList<>(Collections.nCopies(
                n, 0)
        );
        int[] count = new int[10];

        for (int num : list)
            count[(num / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            int digit = (list.get(i) / exp) % 10;
            output.set(count[digit] - 1, list.get(i));
            count[digit]--;
        }

        for (int i = 0; i < n; i++)
            list.set(i, output.get(i));
    }
}
