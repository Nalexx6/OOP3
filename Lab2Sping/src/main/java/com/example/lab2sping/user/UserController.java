package com.example.lab2sping.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    public User login(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping(path="{userId}")
    public User getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }


    @PostMapping
    public void signUp(@RequestBody User user, @RequestHeader("Authorization") String authString){
        userService.addUser(user, authString);
    }

    @PutMapping(path="/block/{userId}")
    public void blockUser(@PathVariable("userId") Long userId){
        userService.blockUser(userId);
    }

    @PutMapping(path="/unblock/{userId}")
    public void unblockUser(@PathVariable("userId") Long userId){
        userService.unblockUser(userId);
    }

}
