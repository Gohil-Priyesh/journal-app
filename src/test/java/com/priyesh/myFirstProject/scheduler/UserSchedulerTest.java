package com.priyesh.myFirstProject.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    UserScheduler userScheduler;

    @Test
    public void testFetchUsersAndSaMail(){
        userScheduler.fetchUserAndSendMai();
    }

}
