package com.codecool.turtleevent.controller;

import com.codecool.turtleevent.model.Event;
import com.codecool.turtleevent.model.dto.EventDTO;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event/")
public class EventController {

    private EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("all")
    public List<EventDTO> getAllUsers(){
        return eventService.getAllEvent();
    }



    @PostMapping("create")
    public RestResponseDTO createEvent(@RequestBody EventDTO newEvent) {
        return eventService.addEvent(newEvent);
    }

    @GetMapping("{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.findEventDTOById(id);
    }

    @DeleteMapping("delete")
    public RestResponseDTO deleteEventById(@RequestBody IdDTO id) {
        return eventService.deleteEvent(id);
    }

    @PutMapping("update")
    public RestResponseDTO updateEventById(@RequestBody EventDTO newEvent) {
        return eventService.updateEvent(newEvent);
    }

}
