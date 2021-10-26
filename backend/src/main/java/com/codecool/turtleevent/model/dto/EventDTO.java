package com.codecool.turtleevent.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;

public class EventDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime toDate;
    private Set<Long> userRoleIds;
    private Set<Long> toBringIds;
    private Set<Long> toDoIds;
    private Set<Long> messagesIds;
    private LocalDateTime createTime;

    public EventDTO() {
    }

    public EventDTO(Long id, String name, String description, String location, LocalDateTime fromDate, LocalDateTime toDate,
                    Set<Long> userRoleIds, Set<Long> toBringIds, Set<Long> toDoIds, Set<Long> messagesIds,
                    LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.userRoleIds = userRoleIds;
        this.toBringIds = toBringIds;
        this.toDoIds = toDoIds;
        this.messagesIds = messagesIds;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public Set<Long> getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(Set<Long> userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    public Set<Long> getToBringIds() {
        return toBringIds;
    }

    public void setToBringIds(Set<Long> toBringIds) {
        this.toBringIds = toBringIds;
    }

    public Set<Long> getToDoIds() {
        return toDoIds;
    }

    public void setToDoIds(Set<Long> toDoIds) {
        this.toDoIds = toDoIds;
    }

    public Set<Long> getMessagesIds() {
        return messagesIds;
    }

    public void setMessagesIds(Set<Long> messagesIds) {
        this.messagesIds = messagesIds;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
