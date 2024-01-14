package com.gridnine.testing.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Segment {
    private final LocalDateTime departure;
    private final LocalDateTime arrival;

    public Segment(LocalDateTime departure, LocalDateTime arrival) {
        this.departure = departure;
        this.arrival = arrival;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Segment)) return false;
        Segment segment = (Segment) o;
        return Objects.equals(departure, segment.departure) && Objects.equals(arrival, segment.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, arrival);
    }

    @Override
    public String toString() {
        return "Segment{" +
                "departure=" + departure +
                ", arrival=" + arrival +
                '}';
    }
}
