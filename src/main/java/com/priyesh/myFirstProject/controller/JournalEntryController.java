package com.priyesh.myFirstProject.controller;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyesh.myFirstProject.entity.JournalEntryEntity;

@RestController
@RequestMapping("/_journalEntries")
public class JournalEntryController {

    private Map<ObjectId, JournalEntryEntity> map = new HashMap<>();

    @GetMapping
    public Map<ObjectId, JournalEntryEntity> getJournal() {
        return map;
    }

    @PostMapping
    public void postJournal(@RequestBody JournalEntryEntity myEntry) {
        map.put(myEntry.getId(), myEntry);
    }

    @GetMapping("/id/{id}")
    public JournalEntryEntity getMyJournal(@PathVariable ObjectId id) {
        return map.get(id);
    }

    @DeleteMapping("/id/{id}")
    public JournalEntryEntity deleteJournalEntry(@PathVariable ObjectId id) {
        map.remove(id);
        return map.remove(id);
    }

    @PutMapping("/id/{id}")
    public JournalEntryEntity updatedJournEntry(@PathVariable ObjectId id, @RequestBody JournalEntryEntity body) {
        return map.put(id, body);
    }

}
