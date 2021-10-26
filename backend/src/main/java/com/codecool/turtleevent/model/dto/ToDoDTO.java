package com.codecool.turtleevent.model.dto;

import com.codecool.turtleevent.model.Doer;
import com.codecool.turtleevent.model.Event;

import java.util.HashSet;
import java.util.Set;

public class ToDoDTO {

    private Long id;
    private Long eventId;
    private String title;
    private Set<Long> doersId;

    public ToDoDTO() {
    }

    public ToDoDTO(Long id, Long eventId, String title, Set<Long> doersId) {
        this.id = id;
        this.eventId = eventId;
        this.title = title;
        this.doersId = doersId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Long> getDoersId() {
        return doersId;
    }

    public void setDoersId(Set<Long> doersId) {
        this.doersId = doersId;
    }
}
