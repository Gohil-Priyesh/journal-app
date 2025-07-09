package com.priyesh.myFirstProject.repository;

import com.priyesh.myFirstProject.entity.ConfigJournalAppEntity;
import com.priyesh.myFirstProject.entity.JournalEntryEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
