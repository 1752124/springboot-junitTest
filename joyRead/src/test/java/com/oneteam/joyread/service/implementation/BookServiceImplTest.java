package com.oneteam.joyread.service.implementation;

import com.oneteam.joyread.dto.BasicBookInfoDTO;
import com.oneteam.joyread.service.BookService;
import com.oneteam.joyread.service.RecordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Autowired
    RecordService recordService;

    List<String> isbn=new ArrayList<>();
    List<Integer>id=new ArrayList<>();
    List<Integer>status=new ArrayList<>();
    List<Float>point=new ArrayList<>();


    @Before
    public void setUp() throws Exception {

        id.add(0);
        id.add(1);
        id.add(1);
        isbn.add("");
        isbn.add(null);
        isbn.add("9787530219218");
        status.add(2);
        status.add(0);
        status.add(4);
        point.add((float) 4.0);
        point.add((float) -1);
        point.add((float) 4.5);

    }

    @Test
    public void getUserBookList() {
        for (int i = 0; i < 3; i++) {
            System.out.println("=====================");
            bookService.getAllUserbook(id.get(i), status.get(i));
        }
    }

    @Test
    public void addBookToReadylist() {
        for (int i = 0; i < 3; i++) {
            System.out.println("=====================");
            bookService.getUserbook(id.get(i), isbn.get(i));
        }
    }

    @Test
    public void finishReadUserbook() {
        for (int i = 0; i < 3; i++) {
            System.out.println("=====================");
            bookService.getUserbook(id.get(i), isbn.get(i));
        }
    }

    @Test
    public void getUserData() {
        for (int i = 0; i < 3; i++) {
            System.out.println("=====================");
            recordService.getTotalDays(id.get(i));
            bookService.getAll(id.get(i));
            recordService.getTotalRecords(id.get(i));
        }
    }

}
