package com.codecool.turtleevent.controller;

import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.MessageDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/message/")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("all")
    public List<MessageDTO> getAll(){
        return messageService.getAll();
    }

    @GetMapping("by-event")
    public List<MessageDTO> getAllByEvent(@RequestBody IdDTO id){
        return messageService.getAllByEvent(id);
    }

    @GetMapping("by-user")
    public List<MessageDTO> getAllByUser(@RequestBody IdDTO id){
        return messageService.getAllByUser(id);
    }

    @PostMapping("add")
    public RestResponseDTO addMessage(@RequestBody MessageDTO message) {
        return messageService.addMessage(message);
    }

    @DeleteMapping("delete")
    public RestResponseDTO deleteMessage(@RequestBody IdDTO messageId) {
        return messageService.delete(messageId);
    }

    @PutMapping("update")
    public RestResponseDTO updateMessage(@RequestBody MessageDTO message) {
        return messageService.update(message);
    }
}
