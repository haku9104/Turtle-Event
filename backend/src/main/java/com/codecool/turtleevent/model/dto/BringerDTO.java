package com.codecool.turtleevent.model.dto;

public class BringerDTO {

    private Long id;
    private Long toBringId;
    private Long userId;
    private double price;
    private int amount;
    private String attachment;

    public BringerDTO() {
    }

    public BringerDTO(Long id, Long toBringId, Long userId, double price, int amount, String attachment) {
        this.id = id;
        this.toBringId = toBringId;
        this.userId = userId;
        this.price = price;
        this.amount = amount;
        this.attachment = attachment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToBringId() {
        return toBringId;
    }

    public void setToBringId(Long toBringId) {
        this.toBringId = toBringId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
