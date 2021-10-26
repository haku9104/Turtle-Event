package com.codecool.turtleevent.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="doers")
public class Doer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "to_do_id", referencedColumnName = "id")
    @JsonBackReference(value="doers-todo")
    private ToDo toDo;
    @ManyToOne
    private User user;
    private String attachment;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    public Doer() {
    }

    public Doer(ToDo toDo, User user, String attachment, LocalDateTime createTime) {
        this.toDo = toDo;
        this.user = user;
        this.attachment = attachment;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ToDo getToDo() {
        return toDo;
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
