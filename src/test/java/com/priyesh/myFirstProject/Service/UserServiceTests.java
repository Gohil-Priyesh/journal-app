package com.priyesh.myFirstProject.Service;

import com.priyesh.myFirstProject.entity.UserEntity;
import com.priyesh.myFirstProject.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvSources;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName(){
        assertEquals(4 , 2+2);
        assertNotNull(userRepository.findByUserName("Ram"));
        UserEntity user = userRepository.findByUserName("Ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Ram",
            "priyesh",
            "amit"
    })
    public void paraTest(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for"+name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,6"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
