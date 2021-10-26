package com.codecool.turtleevent.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="to_bring")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ToBring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @JsonBackReference(value="event-tobring")
    private Event event;

    private String title;
    private String comment;
    @Column(name = "sub_amount")
    private int subAmount;
    @Column(name = "total_amount")
    private int totalAmount;
    @OneToMany(mappedBy = "toBring", cascade = {CascadeType.ALL})
    @JsonManagedReference(value="bringers-tobring")
    private Set<Bringer> bringers;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    public ToBring() {
    }

    public ToBring(Event event, String title, String comment, int totalAmount, LocalDateTime createTime) {
        this.event = event;
        this.title = title;
        this.comment = comment;
        this.subAmount = 0;
        this.totalAmount = totalAmount;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(int subAmount) {
        this.subAmount = subAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<Bringer> getBringers() {
        return bringers;
    }

    public void setBringers(Set<Bringer> bringers) {
        this.bringers = bringers;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
