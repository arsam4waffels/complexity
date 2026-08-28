package org.complexity.scoring;

import org.complexity.model.Algorithm;
/*
 * A simple container for an algorithm and its score.
 * Like a report card — but algorithms don't cry when they get a bad grade unlike you
 */
public class AlgorithmScore {
    private final Algorithm algorithm;
    private int score;
    private String reason;
    public AlgorithmScore(Algorithm algorithm) {
        this.algorithm = algorithm;
        this.score = 0;
        this.reason = "";
    }
    public void addScore(int points,
                         String reason) {
        this.score += points;
        this.reason += reason + "\n";
    }
    public Algorithm getAlgorithm() {
        return algorithm;
    }
    public int getScore() {
        return score;
    }
    public String getReason() {
        return reason;
    }
}
