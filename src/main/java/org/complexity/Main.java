package org.complexity;

import org.complexity.analyzer.ArrayAnalyzer;
import org.complexity.model.Algorithm;
import org.complexity.model.DataProfile;
import org.complexity.model.UserPreference;
import org.complexity.scoring.AlgorithmScore;
import org.complexity.scoring.ScoringEngine;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayAnalyzer<Integer> analyzer = new ArrayAnalyzer<>();
        ScoringEngine engine = new ScoringEngine();

        List<Integer> large =  List.of(1,1,1,2,2,2,3,3,3,1,2,3,1,2,3);
        UserPreference p1 = new UserPreference(true, false, false);

        DataProfile profile = analyzer.analyze(large);
        AlgorithmScore result = engine.recommend(profile, p1);

        System.out.println(profile);
        System.out.println("Recommended: " + result.getAlgorithm().getDisplayName());
        for (Algorithm algo : Algorithm.values()) {
            AlgorithmScore s = engine.evaluatePublic(algo, profile, p1);
            System.out.println(algo.getDisplayName() + " → " + s.getScore());
        }
        System.out.println("Score: " + result.getScore());
        System.out.println("Reasons:\n" + result.getReason());
    }

}