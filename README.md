# Complexity

A sorting algorithm advisor. Give it a list, tell it what you care about,
it tells you which sorting algorithm to use and why.

---

## Project Structure

```angular2html
src/
├── main/java/org/complexity/
│   │   Main.java                ← Entry point
│   │
│   ├── analyzer/
│   │       ArrayAnalyzer.java   ← Analyzes size, sortedness, uniqueness and type
│   │
│   ├── model/
│   │       Algorithm.java       ← Sorting algorithms enum with time/space complexity
│   │       DataProfile.java     ← Snapshot of the analyzed array's characteristics
│   │       UserPreference.java  ← What the user cares about (speed, memory, stability)
│   │
│   └── scoring/
│           AlgorithmScore.java  ← Algorithm + its score + reasons
│           ScoringEngine.java   ← Scores every algorithm and picks the best one
│
└── test/java/org/complexity/
        ArrayAnalyzerTest.java   ← Unit tests for the analyzer
```

---

## The Problem

Picking a sorting algorithm is usually either blind ("just use QuickSort")
or overkill (reading research papers to sort 8 integers).

Complexity sits in the middle.

---

## How It Works

Your List
↓
ArrayAnalyzer → size, sortedness, uniqueness, type
↓
ScoringEngine → scores every algorithm based on data + your preferences
↓
Recommendation → best algorithm + why


---

## What Gets Analyzed

| Property | What it means |
|---|---|
| Size | How many elements |
| Sortedness | How close to sorted (0% = chaos, 100% = perfect) |
| Uniqueness | How many duplicates |
| Type | Integer? Enables Counting/Radix Sort |

## What You Tell It

| Preference | Meaning |
|---|---|
| Speed over Memory | Fast execution vs low memory usage |
| Stable | Does order of equal elements matter? |
| Memory Constrained | Running on limited memory? |

---

## Quick Start

```java
Complexity<Integer> c = new Complexity.Builder<>(
        List.of(3, 1, 4, 1, 5, 9, 2, 6))
        .speedOverMemory(true)
        .needsStable(false)
        .memoryConstrained(false)
        .build();

AlgorithmScore result = c.analyze();
System.out.println(result.getAlgorithm().getDisplayName());
        System.out.println(result.getReason());
```

---

## Built With

- Java 17+
- Maven
- JUnit 5

---

v1.1 — works. future versions will make it better. that's the deal.