package com.festival;

import java.time.LocalTime;
import java.util.Objects;

public record Performance(String artistName, String stage, LocalTime timeSlot) {
    public Performance {
        Objects.requireNonNull(artistName);
        Objects.requireNonNull(stage);
        Objects.requireNonNull(timeSlot);
    }

    public String toString() {
        return String.format("Artist: %-20s | Stage: %-15s | Time: %s", artistName, stage, timeSlot);
    }
}