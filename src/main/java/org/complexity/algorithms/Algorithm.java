package org.complexity.algorithms;

import java.util.List;

public interface Algorithm<T extends Comparable<T>> {
    List<T> sort(List<T> list);
}
