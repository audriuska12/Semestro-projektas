package com.tvarkarastis.entity;

import com.tvarkarastis.dao.UserManagerDao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by audri on 2017-04-22.
 */
public class Event {
    private int host;
    private boolean isPublic;
    private String name, location;
    private LocalDateTime start, end;
    private int id;

    public Event() {
    }

    public boolean validate(){
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

    public boolean isOpen() {
        return isPublic;
    }
    public void setOpen(boolean isOpen) {
        isPublic = isOpen;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getHostUser() {
        return UserManagerDao.getUser(getHost());
    }

    public void setStartDateTime(Timestamp dateTime) {
        start = dateTime.toLocalDateTime();
    }

    public Timestamp getStartDateTime() {
        return Timestamp.valueOf(start);
    }

    public void setEndDateTime(Timestamp dateTime) {
        end = dateTime.toLocalDateTime();
    }

    public Timestamp getEndDateTime() {
        return Timestamp.valueOf(end);
    }

    @Override
    public String toString() {
        return "Event{" +
                "host=" + host +
                ", isPublic=" + isPublic +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", id=" + id +
                '}';
    }
}
