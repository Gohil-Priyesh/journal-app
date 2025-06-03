package com.priyesh.myFirstProject.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.priyesh.myFirstProject.entity.JournalEntryEntity;

public interface JournalEntryRepository extends MongoRepository<JournalEntryEntity, ObjectId> {

}
