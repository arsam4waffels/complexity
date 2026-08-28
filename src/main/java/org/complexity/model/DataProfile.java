package org.complexity.model;

public class DataProfile {
    private final int size;
    private final double sortedness;
    private final double uniqueness;
    private final boolean isIntegerType;
    public DataProfile(int size,
                       double sortedness,
                       double uniqueness,
                       boolean isIntegerType) {
        this.size = size;
        this.sortedness = sortedness;
        this.uniqueness = uniqueness;
        this.isIntegerType = isIntegerType;
    }
    public int getSize() {
        return size;
    }
    public double getSortedness() {
        return sortedness;
    }
    public double getUniqueness() {
        return uniqueness;
    }
    public boolean isIntegerType() {
        return isIntegerType;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("==========================================\n");
        stringBuilder
                .append(String.format("%-14s", "[Size]"))
                .append(": ").append(size)
                .append("\n");
        stringBuilder
                .append(String.format("%-14s", "[Sortedness]"))
                .append(": ")
                .append(String.format("%.0f%%", sortedness * 100))
                .append("\n");
        stringBuilder
                .append(String.format("%-14s", "[Uniqueness]"))
                .append(": ")
                .append(String.format("%.0f%%", uniqueness * 100))
                .append("\n");
        stringBuilder
                .append(String.format("%-14s", "[Integer]"))
                .append(": ")
                .append(isIntegerType)
                .append("\n");
        stringBuilder.append("==========================================\n");
        return stringBuilder.toString();
    }
}
