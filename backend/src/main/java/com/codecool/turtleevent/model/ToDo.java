package com.codecool.turtleevent.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "to_do")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonBackReference(value="event-todo")
    private Event event;

    private String title;

    @OneToMany(mappedBy = "toDo", cascade = {CascadeType.ALL})
    @JsonManagedReference(value="doers-todo")
    private Set<Doer> doers = new HashSet<>();

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    public ToDo() {
    }

    public ToDo(Event event, String title, LocalDateTime createTime) {
        this.event = event;
        this.title = title;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Doer> getDoers() {
        return doers;
    }

    public void setDoers(Set<Doer> doers) {
        this.doers = doers;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
