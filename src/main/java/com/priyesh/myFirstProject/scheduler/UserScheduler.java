package com.priyesh.myFirstProject.scheduler;

import com.priyesh.myFirstProject.cache.AppCache;
import com.priyesh.myFirstProject.entity.JournalEntryEntity;
import com.priyesh.myFirstProject.entity.UserEntity;
import com.priyesh.myFirstProject.enums.Sentiments;
import com.priyesh.myFirstProject.repository.UserRepositoryImpl;
import com.priyesh.myFirstProject.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 0 9 ? * SUN *") ///  for every sunday at 9 am
//    @Scheduled(cron = "0 * * ? * *") ///  for scheduling every minute
    public void fetchUserAndSendMai(){
        List<UserEntity> users = userRepositoryImpl.getUserForSA();
        for(UserEntity user : users){
            List<JournalEntryEntity> journalEntries = user.getJournalEntries();
            ///  for filtering the last 7-day journal entries of the user
            List<Sentiments> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());

            Map<Sentiments, Integer> sentimentCount = new HashMap<>();
            for (Sentiments sentiment : sentiments){
                if(sentiment != null){
                    sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0) + 1);
                }
            }
            Sentiments mostFrequentSentiment = null;
            int maxCount = 0;
            for(Map.Entry<Sentiments,Integer> entry : sentimentCount.entrySet()){
                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment != null){
                emailService.sendEmail(user.getEmail(),"Sentiment for last 7-days",mostFrequentSentiment.toString());
            }

        }
    }

    ///  this is also a way to keep refreshing AppCache in 5 minutes using scheduling
//    @Scheduled(cron = "0 0/10 * 1/1 * ? *")
//    public void clearAppCache(){
//        appCache.init();
//    }
}
