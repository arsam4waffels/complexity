package org.complexity;

import org.complexity.analyzer.ArrayAnalyzer;
import org.complexity.model.DataProfile;
import org.complexity.model.UserPreference;
import org.complexity.scoring.AlgorithmScore;
import org.complexity.scoring.ScoringEngine;

import java.util.List;

public class Complexity<T extends Comparable<T>> {
    private final List<T> arrayList;
    private final ArrayAnalyzer<T> arrayAnalyzer;
    private final ScoringEngine scoringEngine;
    private final UserPreference userPreference;
    private Complexity(Builder<T> builder) {
        this.arrayList = builder.arrayList;
        this.arrayAnalyzer = builder.arrayAnalyzer;
        this.scoringEngine = builder.scoringEngine;
        this.userPreference = builder.userPreference;
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
            arrayAnalyzer = new ArrayAnalyzer<>();
            scoringEngine = new ScoringEngine();
            userPreference = new UserPreference(
                    speedOverMemory,
                    needsStable,
                    memoryConstrained
            );
            return new Complexity(this);
        }
    }
    public AlgorithmScore analyze() {
        DataProfile dataProfile = arrayAnalyzer.analyze(arrayList);
        AlgorithmScore algorithmScore = scoringEngine.recommend(dataProfile, userPreference);
        return algorithmScore;
    }
}