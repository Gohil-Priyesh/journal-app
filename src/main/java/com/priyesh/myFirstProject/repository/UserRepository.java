package com.priyesh.myFirstProject.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.priyesh.myFirstProject.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUserName(String userName);

    void deleteByUserName(String userName);
}
