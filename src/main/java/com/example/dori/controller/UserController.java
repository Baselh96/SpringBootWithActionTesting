package com.example.dori.controller;

import com.example.dori.model.User;
import com.example.dori.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    Userservice userservice;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) throws Exception {
        return userservice.create(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) throws Exception {
        return userservice.login(user);
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam long id) {
        return userservice.getUser(id);
    }

    @GetMapping("/getAllFriends")
    public Set<User> getAllFriends(@RequestParam long id) {
        return userservice.getAllFriends(id);
    }

    @GetMapping("/getFriend")
    public User getFriend(@RequestParam long id, long friendId) {
        return userservice.getFriend(id, friendId);
    }

    @GetMapping("/addFriend")
    public boolean addFriend(@RequestParam long id, long friendId) {
        if ( userservice.userFind(id) || userservice.userFind(friendId) || !userservice.isfriend(id, friendId) ) {
            userservice.addFriend(id, friendId);
            return true;
        }
        return false;
    }

}
