package com.codecool.turtleevent.model.dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private Long id;
    private Long authorId;
    private Long eventId;
    private String text;
    private LocalDateTime posted;

    public MessageDTO() {
    }

    public MessageDTO(Long id, Long authorId, Long eventId, String text, LocalDateTime posted) {
        this.id = id;
        this.authorId = authorId;
        this.eventId = eventId;
        this.text = text;
        this.posted = posted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getPosted() {
        return posted;
    }

    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }
}
