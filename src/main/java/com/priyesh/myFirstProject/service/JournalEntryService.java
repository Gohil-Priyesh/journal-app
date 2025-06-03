package com.priyesh.myFirstProject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.priyesh.myFirstProject.entity.JournalEntryEntity;
import com.priyesh.myFirstProject.entity.UserEntity;
import com.priyesh.myFirstProject.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Transactional
    public void saveEntry(JournalEntryEntity journalEntryEntity, String userName) {
        try {
            UserEntity user = userService.findByUserName(userName);
            journalEntryEntity.setDate(LocalDateTime.now());
            JournalEntryEntity saved = journalEntryRepository.save(journalEntryEntity);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            throw e;
        }
    }

    public void saveEntry(JournalEntryEntity journalEntryEntity) {
        journalEntryRepository.save(journalEntryEntity);
    }

    public List<JournalEntryEntity> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntryEntity> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
    try {
        UserEntity user = userService.findByUserName(userName);
         removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(removed){
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
    }catch (Exception e){
        System.out.println(e);
        throw new RuntimeException("An Error occurred while deleting the entry.", e);
    }
    return removed;
    }

}
