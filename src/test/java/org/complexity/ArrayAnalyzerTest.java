package org.complexity;

import org.complexity.analyzer.ArrayAnalyzer;
import org.complexity.model.DataProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayAnalyzerTest {
    private ArrayAnalyzer<Integer> analyzer;
    @BeforeEach
    void setUp() {
        analyzer = new ArrayAnalyzer<>();
    }
    @Test
    void testSize() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        DataProfile profile = analyzer.analyze(list);
        assertEquals(5, profile.getSize());
    }
    @Test
    void testEmptyList() {
        List<Integer> list = List.of();
        DataProfile profile = analyzer.analyze(list);
        assertEquals(0, profile.getSize());
        assertEquals(1.0, profile.getSortedness());
        assertEquals(1.0, profile.getUniqueness());
    }
    @Test
    void testFullySorted() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        DataProfile profile = analyzer.analyze(list);
        assertEquals(1.0, profile.getSortedness());
    }
    @Test
    void testFullyReversed() {
        List<Integer> list = List.of(5, 4, 3, 2, 1);
        DataProfile profile = analyzer.analyze(list);
        assertEquals(0.0, profile.getSortedness());
    }
    @Test
    void testPartiallySorted() {
        List<Integer> list = List.of(1, 2, 4, 3, 5);
        DataProfile profile = analyzer.analyze(list);
        assertTrue(profile.getSortedness() > 0.0 && profile.getSortedness() < 1.0);
    }
    @Test
    void testSingleElement() {
        List<Integer> list = List.of(42);
        DataProfile profile = analyzer.analyze(list);
        assertEquals(1.0, profile.getSortedness());
    }
    @Test
    void testAllUnique() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        DataProfile profile = analyzer.analyze(list);
        assertEquals(1.0, profile.getUniqueness());
    }
    @Test
    void testAllDuplicates() {
        List<Integer> list = List.of(1, 1, 1, 1, 1);
        DataProfile profile = analyzer.analyze(list);
        assertEquals(0.2, profile.getUniqueness(), 0.001);
    }
    @Test
    void testPartialDuplicates() {
        List<Integer> list = List.of(1, 1, 2, 3, 4);
        DataProfile profile = analyzer.analyze(list);
        assertEquals(0.8, profile.getUniqueness(), 0.001);
    }
    @Test
    void testIntegerType() {
        List<Integer> list = List.of(1, 2, 3);
        DataProfile profile = analyzer.analyze(list);
        assertTrue(profile.isIntegerType());
    }
    @Test
    void testStringType() {
        ArrayAnalyzer<String> stringAnalyzer = new ArrayAnalyzer<>();
        List<String> list = List.of("skibidi", "sib", "moz");
        DataProfile profile = stringAnalyzer.analyze(list);
        assertFalse(profile.isIntegerType());
    }
}
