package com.priyesh.myFirstProject.controller;

import com.priyesh.myFirstProject.cache.AppCache;
import com.priyesh.myFirstProject.entity.UserEntity;
import com.priyesh.myFirstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser() {
        List<UserEntity> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createUserAdmin(@RequestBody UserEntity user){
        userService.saveAdmin(user);
    }

    @GetMapping("clear-ap-cache")
    public void clearAppCache(){
        appCache.init(); /// for clearing and reloading new data from mongoDB
    }
}
