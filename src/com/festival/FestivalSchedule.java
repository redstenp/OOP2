package com.festival;

import java.util.*;
import java.util.stream.Collectors;

public class FestivalSchedule {
    private final List<Performance> performances = new ArrayList<>();

    public void addPerformances(Performance... perf) {
        performances.addAll(List.of(perf));
    }

    public List<Performance> getAllPerformances() {
        return performances;
    }

    public Map<String, List<Performance>> getGroupedByStage() {
        return performances.stream()
                .collect(Collectors.groupingBy(Performance::stage));
    }
}
