package com.ctjioman.eatClub.Model;

import java.time.LocalTime;

public class OpenTimeEvent implements Comparable<OpenTimeEvent> {

    LocalTime time;
    boolean isOpen;

    public LocalTime getTime() {
        return time;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public OpenTimeEvent(LocalTime time, boolean isOpen) {
        this.time = time;
        this.isOpen = isOpen;
    }

    @Override
    public int compareTo(OpenTimeEvent other) {
        return this.time.compareTo(other.time);
    }
}