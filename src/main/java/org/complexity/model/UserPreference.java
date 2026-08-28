package org.complexity.model;

public class UserPreference {
    private final boolean speedOverMemory;
    private final boolean needsStable;
    private final boolean memoryConstrained;
    public UserPreference(boolean speedOverMemory,
                          boolean needsStable,
                          boolean memoryConstrained) {
        this.speedOverMemory = speedOverMemory;
        this.needsStable = needsStable;
        this.memoryConstrained = memoryConstrained;
    }
    public boolean isSpeedOverMemory() {
        return speedOverMemory;
    }
    public boolean isNeedsStable() {
        return needsStable;
    }
    public boolean isMemoryConstrained() {
        return memoryConstrained;
    }
}
