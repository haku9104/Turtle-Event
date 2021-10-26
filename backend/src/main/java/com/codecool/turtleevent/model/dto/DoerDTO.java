package com.codecool.turtleevent.model.dto;

public class DoerDTO {

    private Long id;
    private Long toDoId;
    private Long userId;
    private String attachment;

    public DoerDTO() {
    }

    public DoerDTO(Long id, Long toDoId, Long userId, String attachment) {
        this.id = id;
        this.toDoId = toDoId;
        this.userId = userId;
        this.attachment = attachment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToDoId() {
        return toDoId;
    }

    public void setToDoId(Long toDoId) {
        this.toDoId = toDoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
