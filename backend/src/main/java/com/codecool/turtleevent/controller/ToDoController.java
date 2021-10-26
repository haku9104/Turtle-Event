package com.codecool.turtleevent.controller;

import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.model.dto.ToBringDTO;
import com.codecool.turtleevent.model.dto.ToDoDTO;
import com.codecool.turtleevent.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/to-do/")
public class ToDoController {

    private ToDoService toDoService;

    @Autowired
    public void setToDoService(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("all")
    public List<ToDoDTO> getAll() {
        return toDoService.getAll();
    }

    @GetMapping("by-event")
    public List<ToDoDTO> getAllByEvent(@RequestBody IdDTO eventId) {
        return toDoService.getAllByEvent(eventId);
    }

    @PostMapping("add")
    public RestResponseDTO addToDo(@RequestBody ToDoDTO toDo) {
        return toDoService.add(toDo);
    }

    @PutMapping("update")
    public RestResponseDTO updateToDo(@RequestBody ToDoDTO toDo) {
        return toDoService.update(toDo);
    }

    @DeleteMapping("delete")
    public RestResponseDTO deleteToDo(@RequestBody IdDTO id) {
        return toDoService.delete(id);
    }
}
