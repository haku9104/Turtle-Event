package com.codecool.turtleevent.model.dto;

import com.codecool.turtleevent.model.RoleType;

public class UserEventRoleDTO {

    private long id;
    private long userId;
    private long eventId;
    private RoleType role;

    public UserEventRoleDTO() {
    }

    public UserEventRoleDTO(long id, long userId, long eventId, RoleType role) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType roleType) {
        this.role = roleType;
    }
}
