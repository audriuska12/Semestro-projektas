package com.tvarkarastis.entity;

import java.time.LocalDateTime;

/**
 * Created by audri on 2017-04-22.
 */
public class Event {
    private int host;
    private boolean isPublic;
    private String name, location;
    private LocalDateTime start, end;

    public Event() {
    }

    public boolean Validate(){
        if(host < 1) return false;
        if (name == null || name.isEmpty() || name.length() <5 ) return false;
        if(location == null || location.isEmpty() || location.length() <5 ) return false;
        return end.compareTo(start) >= 0;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}
