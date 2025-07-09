package com.priyesh.myFirstProject.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.priyesh.myFirstProject.entity.UserEntity;
import com.priyesh.myFirstProject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(UserEntity userEntity) {
        try {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRoles(Arrays.asList("USER"));
            userRepository.save(userEntity);
        } catch (Exception e) {
            // the {} there in the String is called placeholder
            log.error("error occurred {}",userEntity.getUserName(),e);
            log.debug("error occurred {}",userEntity.getUserName(),e);
            throw new RuntimeException(e);
        }
    }
    public void saveAdmin(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(userEntity);
    }

     public void saveUser(UserEntity user) {
     userRepository.save(user);
     }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
