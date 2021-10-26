package com.codecool.turtleevent.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;
    @Column(name="from_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime fromDate;
    @Column(name="to_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime toDate;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.ALL})
    @JsonManagedReference(value="event-userroles")
    private Set<UserEventRole> userRoles;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.ALL})
    @JsonManagedReference(value="event-tobring")
    private Set<ToBring> toBring;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.ALL})
    @JsonManagedReference(value="event-todo")
    private Set<ToDo> toDo;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.ALL})
    @JsonManagedReference(value="event-messages")
    private Set<Message> messages;

    @Column(name="create_time", nullable = false)
    private LocalDateTime createTime;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<UserEventRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserEventRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<ToBring> getToBring() {
        return toBring;
    }

    public void setToBring(Set<ToBring> toBring) {
        this.toBring = toBring;
    }

    public Set<ToDo> getToDo() {
        return toDo;
    }

    public void setToDo(Set<ToDo> toDo) {
        this.toDo = toDo;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
