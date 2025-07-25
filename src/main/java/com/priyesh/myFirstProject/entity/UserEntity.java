package com.priyesh.myFirstProject.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String userName;

    private String email;

    private boolean sentimentAnalysis;

    @NonNull
    private String password;

    @DBRef
    private List<JournalEntryEntity> journalEntries = new ArrayList<>();

    private List<String> roles;
}
