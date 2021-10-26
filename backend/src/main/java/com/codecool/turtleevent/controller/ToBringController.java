package com.codecool.turtleevent.controller;

import com.codecool.turtleevent.model.ToBring;
import com.codecool.turtleevent.model.UserEventRole;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.model.dto.ToBringDTO;
import com.codecool.turtleevent.service.ToBringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/to-bring/")
public class ToBringController {

    private ToBringService toBringService;

    @Autowired
    public void setToBringService(ToBringService toBringService) {
        this.toBringService = toBringService;
    }

    @GetMapping("all")
    public List<ToBringDTO> getAll() {
        return toBringService.getAll();
    }

    @GetMapping("by-event")
    public List<ToBringDTO> getAllByEvent(@RequestBody IdDTO eventId) {
        return toBringService.getAllByEvent(eventId);
    }

    @PostMapping("add")
    public RestResponseDTO addToBring(@RequestBody ToBringDTO toBring) {
        return toBringService.add(toBring);
    }

    @PutMapping("update")
    public RestResponseDTO updateToBring(@RequestBody ToBringDTO toBring) {
        return toBringService.update(toBring);
    }

    @DeleteMapping("delete")
    public RestResponseDTO deleteToBring(@RequestBody IdDTO id) {
        return toBringService.delete(id);
    }
}
