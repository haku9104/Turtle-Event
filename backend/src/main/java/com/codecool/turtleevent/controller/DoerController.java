package com.codecool.turtleevent.controller;

import com.codecool.turtleevent.model.dto.BringerDTO;
import com.codecool.turtleevent.model.dto.DoerDTO;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.service.DoerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doer/")
public class DoerController {

    private DoerService doerService;

    @Autowired
    public void setDoerService(DoerService doerService) {
        this.doerService = doerService;
    }

    @GetMapping("all")
    public List<DoerDTO> getAll() {
        return doerService.getAll();
    }

    @GetMapping("by-to-do")
    public List<DoerDTO> getAllByToDo(@RequestBody IdDTO toDoId) {
        return doerService.getAllByToDo(toDoId);
    }

    @GetMapping("by-user")
    public List<DoerDTO> getAllByUser(@RequestBody IdDTO userId) {
        return doerService.getAllByUser(userId);
    }

    @PostMapping("add")
    public RestResponseDTO addDoer(@RequestBody DoerDTO doer) {
        return doerService.add(doer);
    }

    @PutMapping("update")
    public RestResponseDTO updateDoer(@RequestBody DoerDTO doer) {
        return doerService.update(doer);
    }

    @DeleteMapping("delete")
    public RestResponseDTO deleteDoer(@RequestBody IdDTO id) {
        return doerService.delete(id);
    }





}
