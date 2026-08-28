# Complexity

A sorting algorithm advisor. Give it a list, tell it what you care about,
it tells you which sorting algorithm to use and why.

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
ArrayAnalyzer<Integer> analyzer = new ArrayAnalyzer<>();
ScoringEngine engine = new ScoringEngine();

List<Integer> myList = List.of(3, 1, 4, 1, 5, 9, 2, 6);
UserPreference preference = new UserPreference(true, false, false);

DataProfile profile = analyzer.analyze(myList);
AlgorithmScore result = engine.recommend(profile, preference);

System.out.println(result.getAlgorithm().getDisplayName());
System.out.println(result.getReason());
```

---

## Built With

- Java 17+
- Maven
- JUnit 5

---

v1.0 — works. future versions will make it better. that's the deal.