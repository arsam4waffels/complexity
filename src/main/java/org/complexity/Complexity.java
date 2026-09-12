package org.complexity;

import org.complexity.algorithms.CountingSortAlgorithm;
import org.complexity.algorithms.HeapSortAlgorithm;
import org.complexity.algorithms.InsertionSortAlgorithm;
import org.complexity.algorithms.MergeSortAlgorithm;
import org.complexity.algorithms.QuickSortAlgorithm;
import org.complexity.algorithms.RadixSortAlgorithm;
import org.complexity.algorithms.SortingAlgorithm;
import org.complexity.algorithms.TimSortAlgorithm;
import org.complexity.analyzer.ArrayAnalyzer;
import org.complexity.model.Algorithm;
import org.complexity.model.DataProfile;
import org.complexity.model.UserPreference;
import org.complexity.scoring.AlgorithmScore;
import org.complexity.scoring.ScoringEngine;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Complexity<T extends Comparable<T>> {
    private final List<T> arrayList;
    private final ArrayAnalyzer<T> arrayAnalyzer;
    private final ScoringEngine scoringEngine;
    private final UserPreference userPreference;
    private final Map<Algorithm, SortingAlgorithm<T>> algorithms;
    private Complexity(Builder<T> builder) {
        this.arrayList = builder.arrayList;
        this.arrayAnalyzer = builder.arrayAnalyzer;
        this.scoringEngine = builder.scoringEngine;
        this.userPreference = builder.userPreference;
        this.algorithms = buildAlgorithmMap();
    }

    /**
     * <p>Maps each algorithm to its implementation and executes the selected one.</p>
     * <p><b>Sorting and analysis are intentionally separate</b> — you don't need to analyze
     * before sorting, and you don't need to sort after analyzing.</p>
     */
    @SuppressWarnings("unchecked")
    private Map<Algorithm, SortingAlgorithm<T>> buildAlgorithmMap() {

        Map<Algorithm, SortingAlgorithm<T>> map = new EnumMap<>(Algorithm.class);

        map.put(Algorithm.QUICKSORT, (SortingAlgorithm<T>) new QuickSortAlgorithm<>());
        map.put(Algorithm.MERGESORT, (SortingAlgorithm<T>) new MergeSortAlgorithm<>());
        map.put(Algorithm.TIMSORT, (SortingAlgorithm<T>) new TimSortAlgorithm<>());
        map.put(Algorithm.HEAPSORT, (SortingAlgorithm<T>) new HeapSortAlgorithm<>());
        map.put(Algorithm.INSERTION_SORT, (SortingAlgorithm<T>) new InsertionSortAlgorithm<>());
        map.put(Algorithm.COUNTING_SORT, (SortingAlgorithm<T>) new CountingSortAlgorithm());
        map.put(Algorithm.RADIX_SORT, (SortingAlgorithm<T>) new RadixSortAlgorithm());

        return map;
    }

    /**
     * <b>Sorts the list using the given algorithm.</b>
     * <p>Call this directly if you already know which algorithm you want,
     * or pair it with analyze() to let the system decide for you.</p>
     *
     * @param algorithm the algorithm to use
     * @return a new sorted list — the original is never modified
     * @throws IllegalArgumentException if the algorithm is not supported
     */
    public List<T> sort(Algorithm algorithm) {
        SortingAlgorithm<T> sorter = algorithms.get(algorithm);
        if (sorter == null)
            throw new IllegalArgumentException("Algorithm not supported: " + algorithm);
        return sorter.sort(arrayList);
    }

    public static class Builder<T extends Comparable<T>> {

        private final List<T> arrayList;
        private boolean speedOverMemory;
        private boolean needsStable;
        private boolean memoryConstrained;

        public Builder(List<T> arrayList) {
            this.arrayList = arrayList;
        }
        public Builder speedOverMemory(boolean speedOverMemory) {
            this.speedOverMemory = speedOverMemory;
            return this;
        }
        public Builder needsStable(boolean needsStable) {
            this.needsStable = needsStable;
            return this;
        }
        public Builder memoryConstrained(boolean memoryConstrained) {
            this.memoryConstrained = memoryConstrained;
            return this;
        }

        ArrayAnalyzer<T> arrayAnalyzer;
        ScoringEngine scoringEngine;
        UserPreference userPreference;

        public Complexity build() {

            validation();

            arrayAnalyzer = new ArrayAnalyzer<>();
            scoringEngine = new ScoringEngine();
            userPreference = new UserPreference(
                    speedOverMemory,
                    needsStable,
                    memoryConstrained
            );

            return new Complexity(this);
        }
        /**
         * <h5>Array rejection causes</h5>
         * <p>Complexity needs a list for recommending the best and efficient
         * sorting algorithm. Any value or action with undefine behavior will be rejected.</p>
         * <b>Causes of exceptions are : </b>
         * <ul>
         *     <li>{@code null} : list cannot be null or contain null.</li>
         *     <li>{@code Empty} : list cannot be empty.</li>
         * </ul>
         */
        private void validation() {

            if (arrayList == null)
                throw new IllegalArgumentException("List cannot be null");

            if (arrayList.isEmpty())
                throw new IllegalArgumentException("List cannot be empty");

            try {

                if (arrayList.contains(null))
                    throw new IllegalArgumentException("List cannot contain null elements");

            } catch (NullPointerException e) {
                throw new IllegalArgumentException("List cannot contain null elements");
            }

        }
    }
    public AlgorithmScore analyze() {

        DataProfile dataProfile = arrayAnalyzer.analyze(arrayList);
        AlgorithmScore algorithmScore = scoringEngine.recommend(dataProfile, userPreference);

        return algorithmScore;
    }
}