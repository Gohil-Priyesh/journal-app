package com.priyesh.myFirstProject.cache;

import com.priyesh.myFirstProject.entity.ConfigJournalAppEntity;
import com.priyesh.myFirstProject.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys {
        WEATHER_API;
    }
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> APP_CACHE;

    /// due to the @PostConstruct annotation as soon the bean of the AppCache class is create
    /// the init method will be called
    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();/// re initializing the app cache
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all){ ///  Bring all keys and values form mongoDB
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());/// add that data from mongo db in appCache variable
        }
    }

}
