package com.codecool.turtleevent.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="bringers")
public class Bringer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "to_bring_id", referencedColumnName = "id")
    @JsonBackReference(value="bringers-tobring")
    private ToBring toBring;
    @ManyToOne
    private User user;
    private double price;
    private int amount;
    private String attachment;
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    public Bringer() {
    }

    public Bringer(ToBring toBring, User user, double price, int amount, String attachment, LocalDateTime createTime) {
        this.toBring = toBring;
        this.user = user;
        this.price = price;
        this.amount = amount;
        this.attachment = attachment;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ToBring getToBring() {
        return toBring;
    }

    public void setToBring(ToBring toBring) {
        this.toBring = toBring;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
