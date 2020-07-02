package com.oneteam.joyread.controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BooklistControllerTest {

    @Autowired
    BooklistController booklistController;

    List<Integer> listId=new ArrayList<>();
    List<Boolean> resultList=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        for(int i=-1;i<4;i++){
            listId.add(i);
        }
        resultList.add(false);
        resultList.add(false);
        resultList.add(true);
        resultList.add(true);
        resultList.add(false);
    }

    @Test
    public void getBooklistInformation() {
        for(int i=0;i<5;i++){
            boolean actual= booklistController.getBooklistInformation(listId.get(i)).hasBody();
            System.out.println("actual: "+actual+" ;expected: "+resultList.get(i));
            assertEquals(resultList.get(i),actual);
        }
    }
}