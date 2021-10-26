package com.codecool.turtleevent.model.dto;

import com.codecool.turtleevent.model.Message;
import com.codecool.turtleevent.model.User;
import com.codecool.turtleevent.model.UserEventRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Long> friendIds;
    private List<Long> friendOfIds;
    private Set<Long> eventRolesIds;
    private Set<Long> messagesIds;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime registered;

    public UserDTO(Long id, String userName, String firstName,
                   String lastName, String email, String password,
                   List<Long> friendIds, List<Long> friendOfIds,
                   Set<Long> eventRolesIds, Set<Long> messagesIds,
                   LocalDateTime registered) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.friendIds = friendIds;
        this.friendOfIds = friendOfIds;
        this.eventRolesIds = eventRolesIds;
        this.messagesIds = messagesIds;
        this.registered = registered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Long> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(List<Long> friendIds) {
        this.friendIds = friendIds;
    }

    public List<Long> getFriendOfIds() {
        return friendOfIds;
    }

    public void setFriendOfIds(List<Long> friendOfIds) {
        this.friendOfIds = friendOfIds;
    }

    public Set<Long> getEventRolesIds() {
        return eventRolesIds;
    }

    public void setEventRolesIds(Set<Long> eventRolesIds) {
        this.eventRolesIds = eventRolesIds;
    }

    public Set<Long> getMessagesIds() {
        return messagesIds;
    }

    public void setMessagesIds(Set<Long> messagesIds) {
        this.messagesIds = messagesIds;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }
}

