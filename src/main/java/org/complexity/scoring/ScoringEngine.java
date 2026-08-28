package org.complexity.scoring;

import org.complexity.model.Algorithm;
import org.complexity.model.DataProfile;
import org.complexity.model.UserPreference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/*
 * The brain of the operation.
 * Evaluates every algorithm and scores them based on the data profile
 * and user preferences.
 *
 * Fair warning: the thresholds in here are hardcoded. Yes, I know.
 * No, I'm not proud of it. But hey — v1 ships, v2 fixes.
 * Future-me will make this configurable. Future-me is very optimistic. Future-me is handsome.
 */
public class ScoringEngine {
    public AlgorithmScore recommend(DataProfile profile, UserPreference preference) {
        List<AlgorithmScore> scores = new ArrayList<>();
        for (Algorithm algo : Algorithm.values())
            scores.add(evaluate(algo, profile, preference));
        return scores.stream()
                .max(Comparator.comparingInt(AlgorithmScore::getScore))
                .orElseThrow();
    }
    private AlgorithmScore evaluate(Algorithm algo,
                                    DataProfile profile,
                                    UserPreference preference) {
        AlgorithmScore score = new AlgorithmScore(algo);

        if (profile.getSize() < 10)
            if (algo == Algorithm.INSERTION_SORT)
                score.addScore(30, "Small array — Insertion Sort has low overhead");
        if (profile.getSize() >= 10)
            if (algo == Algorithm.QUICKSORT || algo == Algorithm.MERGESORT || algo == Algorithm.TIMSORT)
                score.addScore(20, "Large array — needs O(n log n) algorithm");

        if (profile.getSortedness() >= 0.8) {
            if (algo == Algorithm.INSERTION_SORT || algo == Algorithm.TIMSORT)
                score.addScore(25, "Array is nearly sorted — adaptive algorithms win");
            if (algo == Algorithm.QUICKSORT)
                score.addScore(-15, "Nearly sorted arrays are QuickSort's worst case");
        }
        if (profile.getSortedness() < 0.3) {
            if (algo == Algorithm.INSERTION_SORT)
                score.addScore(-30, "Array is highly unsorted — Insertion Sort's worst case");
            if (algo == Algorithm.QUICKSORT || algo == Algorithm.MERGESORT)
                score.addScore(15, "Unsorted data — divide and conquer algorithms shine");
        }

        if (profile.getUniqueness() < 0.5)
            if (algo == Algorithm.COUNTING_SORT || algo == Algorithm.RADIX_SORT)
                score.addScore(20, "Many duplicates — linear sort algorithms shine");

        if (profile.isIntegerType()) {
            if (algo == Algorithm.COUNTING_SORT || algo == Algorithm.RADIX_SORT)
                score.addScore(15, "Integer data — Counting/Radix Sort applicable");
        } else {
            if (algo == Algorithm.COUNTING_SORT || algo == Algorithm.RADIX_SORT)
                score.addScore(-100, "Not integer data — cannot apply");
        }

        if (preference.isSpeedOverMemory()) {
            if (algo == Algorithm.QUICKSORT)
                score.addScore(20, "Speed priority — QuickSort is fast in practice");
            if (algo == Algorithm.MERGESORT)
                score.addScore(-10, "MergeSort uses O(n) extra memory");
        }

        if (preference.isMemoryConstrained()) {
            if (algo == Algorithm.HEAPSORT)
                score.addScore(25, "Memory constrained — HeapSort is O(1) space");
            if (algo == Algorithm.MERGESORT)
                score.addScore(-20, "MergeSort needs O(n) extra memory");
        }

        if (preference.isNeedsStable()) {
            if (algo == Algorithm.QUICKSORT || algo == Algorithm.HEAPSORT)
                score.addScore(-25, "Not stable — equal elements may reorder");
            if (algo == Algorithm.MERGESORT || algo == Algorithm.TIMSORT || algo == Algorithm.INSERTION_SORT)
                score.addScore(20, "Stable algorithm — equal elements keep order");
        }

        if (profile.getUniqueness() < 0.3 && profile.isIntegerType()) {
            if (algo == Algorithm.COUNTING_SORT)
                score.addScore(20, "Very high duplicates + integer — CountingSort optimal");
        }

        return score;
    }
    public AlgorithmScore evaluatePublic(Algorithm algo, DataProfile profile, UserPreference preference) {
        return evaluate(algo, profile, preference);
    }
}
