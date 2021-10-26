package com.codecool.turtleevent.controller;

import com.codecool.turtleevent.model.User;
import com.codecool.turtleevent.model.dto.AddFriendDTO;
import com.codecool.turtleevent.model.dto.IdDTO;
import com.codecool.turtleevent.model.dto.RestResponseDTO;
import com.codecool.turtleevent.model.dto.UserDTO;
import com.codecool.turtleevent.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("profile/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userService.findUserDTOById(id);
    }

    @PostMapping(value = "registration")
    public RestResponseDTO registerUser(@RequestBody UserDTO newUser){
        return userService.addUser(newUser);
    }

    @GetMapping("all")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUser();
    }

    @DeleteMapping("delete")
    public RestResponseDTO deleteUserById(@RequestBody IdDTO user) {
        return userService.deleteUserById(user.getId());
    }

    @PutMapping("update")
    public RestResponseDTO update(@RequestBody UserDTO newUser){
        return userService.updateUser(newUser);
    }

    @PutMapping("add-friend")
    public RestResponseDTO addFriend(@RequestBody AddFriendDTO addFriendDTO){
        return userService.addFriend(addFriendDTO);
    }

}
