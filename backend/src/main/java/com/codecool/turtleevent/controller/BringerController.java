package com.codecool.turtleevent.controller;

import com.codecool.turtleevent.model.dto.BringerDTO;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.service.BringerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bringer/")
public class BringerController {

    private BringerService bringerService;

    @Autowired
    public void setBringerService(BringerService bringerService) {
        this.bringerService = bringerService;
    }

    @GetMapping("all")
    public List<BringerDTO> getAll() {
        return bringerService.getAll();
    }

    @GetMapping("by-to-bring")
    public List<BringerDTO> getAllByToBring(@RequestBody IdDTO toBringId) {
        return bringerService.getAllByToBring(toBringId);
    }

    @GetMapping("by-user")
    public List<BringerDTO> getAllByUser(@RequestBody IdDTO userId) {
        return bringerService.getAllByUser(userId);
    }

    @PostMapping("add")
    public RestResponseDTO addBringer(@RequestBody BringerDTO bringer) {
        return bringerService.add(bringer);
    }

    @PutMapping("increase-amount")
    public  RestResponseDTO increaseAmount(@RequestBody IdDTO id) {
        return bringerService.increaseAmount(id);
    }

    @PutMapping("decrease-amount")
    public  RestResponseDTO decreaseAmount(@RequestBody IdDTO id) {
        return bringerService.decreaseAmount(id);
    }

    @PutMapping("set-amount")
    public  RestResponseDTO setAmount(@RequestBody IdDTO id, @RequestBody int amount) {
        return bringerService.changeAmount(id, amount);
    }

    @PutMapping("update")
    public RestResponseDTO updateBringer(@RequestBody BringerDTO bringer) {
        return bringerService.update(bringer);
    }

    @DeleteMapping("delete")
    public RestResponseDTO deleteBringer(@RequestBody IdDTO id) {
        return bringerService.delete(id);
    }
}
