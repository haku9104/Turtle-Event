package com.codecool.turtleevent.model.dto;

import java.util.Set;

public class ToBringDTO {

    private Long id;
    private long eventId;
    private String title;
    private String comment;
    private int subAmount;
    private int totalAmount;
    private Set<Long> bringerIDs;

    public ToBringDTO() {
    }

    public ToBringDTO(long eventId, String title, String comment) {
        this.eventId = eventId;
        this.title = title;
        this.comment = comment;
    }

    public ToBringDTO(Long id, long eventId, String title, String comment, int subAmount, int totalAmount, Set<Long> bringerIDs) {
        this.id = id;
        this.eventId = eventId;
        this.title = title;
        this.comment = comment;
        this.subAmount = subAmount;
        this.totalAmount = totalAmount;
        this.bringerIDs = bringerIDs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
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

    public Set<Long> getBringerIDs() {
        return bringerIDs;
    }

    public void setBringerIDs(Set<Long> bringerIDs) {
        this.bringerIDs = bringerIDs;
    }
}
