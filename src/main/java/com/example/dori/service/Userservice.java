package com.example.dori.service;

import com.example.dori.model.User;
import com.example.dori.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class Userservice {
    @Autowired
    UserRepo userRepo;

    public User create(User user) throws Exception {
        checkUser(user);
        return  userRepo.save(user);
    }

    public void checkUser(User user) throws Exception {
        if(user.getName() == null || user.getEmail()  == null || user.getPassword()  == null )
            throw new Exception("User hat nicht alle seine Parameter");
    }

    public User login(User user) throws Exception {
        //System.out.println(create(user));
        User expectedUser = userRepo.findByEmail(user.getEmail());

        if(expectedUser == null) {
            throw new Exception("Email is not correct");
        }
        if( !expectedUser.getPassword().equals(user.getPassword()) ) {
            throw new Exception("Password is not correct");
        }
        return  expectedUser;
    }
    public User getUser(long id) {
        return userFind(id) ? userRepo.findById(id).orElse(null): null;
    }
    public boolean userFind(long id){
        return userRepo.existsById(id);
    }

    public Set<User> getAllFriends(long id) {
        return userFind(id) ? userRepo.findById(id).get().getFrieds(): null;
    }

    public User getFriend(long id, long friendId) {
        return !userFind(id) || !userFind(friendId) || !isfriend(id, friendId) ? null : getUser(friendId);
    }
    public boolean isfriend(long id, long friendId) {
        User user = getUser(id);
        User friend = getUser(friendId);
        return user.getFrieds().contains(friend);
    }

    public void addFriend(long id, long friendId) {
        User user = getUser(id);
        User friend = getUser(friendId);
        user.getFrieds().add(friend);
        userRepo.save(user);
    }

}
