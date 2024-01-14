package com.gridnine.testing.Model;

import java.util.List;
import java.util.Objects;

public class Flight {
    private final List<Segment> segments;

    public Flight(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(segments, flight.segments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segments);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "segments=" + segments +
                '}';
    }
}
