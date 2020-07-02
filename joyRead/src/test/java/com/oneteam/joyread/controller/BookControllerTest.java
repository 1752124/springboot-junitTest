package com.oneteam.joyread.controller;

import com.alibaba.fastjson.JSON;
import com.oneteam.joyread.entity.Book;
import com.alibaba.fastjson.JSONObject;
import com.oneteam.joyread.dto.BasicBookInfoDTO;
import com.oneteam.joyread.entity.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class BookControllerTest {

    @Autowired
    BookController bookController;

    @Autowired
    UserController userController;

    List<String>isbn=new ArrayList<>();
    List<Integer>id=new ArrayList<>();
    List<Integer>status=new ArrayList<>();
    List<Float>point=new ArrayList<>();

    Boolean judge1(int id_index,int isbn_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index)+";isbn:"+isbn.get(isbn_index));
        return bookController.getDetailBookInfo(id.get(id_index),isbn.get(isbn_index)).hasBody();
    }

    Boolean judge1_empty(int id_index,int isbn_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index)+";isbn:"+isbn.get(isbn_index));
        String res = bookController.getDetailBookInfo(id.get(id_index),isbn.get(isbn_index)).getBody().toString();
        return !res.equals("[]");
    }

    Boolean judge2(int status_index,int id_index){
        System.out.println("=====================");
        System.out.println("status:"+status.get(status_index)+";userID:"+id.get(id_index));
        return bookController.getUserBookList(status.get(status_index),id.get(id_index)).hasBody();
    }

    Boolean judge2_empty(int status_index,int id_index){
        System.out.println("=====================");
        System.out.println("status:"+status.get(status_index)+";userID:"+id.get(id_index));
        String res = bookController.getUserBookList(status.get(status_index),id.get(id_index)).getBody().toString();
        return !res.equals("[]");
    }

    Boolean judge3(int id_index,int isbn_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index)+";isbn:"+isbn.get(isbn_index));
        return bookController.addBookToReadylist(id.get(id_index),isbn.get(isbn_index)).hasBody();
    }

    Boolean judge3_empty(int id_index,int isbn_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index)+";isbn:"+isbn.get(isbn_index));
        String res = bookController.addBookToReadylist(id.get(id_index),isbn.get(isbn_index)).getBody().toString();
        return !res.equals("[]");
    }

    Boolean judge4(int id_index,int isbn_index, float point_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index)+";isbn:"+isbn.get(isbn_index)+";point:"+point.get((int) point_index));
        return bookController.finishReadUserbook(id.get(id_index),isbn.get(isbn_index),point.get((int) point_index)).hasBody();
    }

    Boolean judge4_empty(int id_index,int isbn_index, float point_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index)+";isbn:"+isbn.get(isbn_index)+";point:"+point.get((int) point_index));
        String res = bookController.finishReadUserbook(id.get(id_index),isbn.get(isbn_index),point.get((int) point_index)).getBody().toString();
        return !res.equals("[]");
    }

    Boolean judge5(int id_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index));
        return userController.getUserData(id.get(id_index)).hasBody();
    }

    Boolean judge5_empty(int id_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index));
        String res=userController.getUserData(id.get(id_index)).getBody().toString();
        return !res.equals("[]");
    }

    private String keyword;
//    List<Record> userBookList=new ArrayList<>();
//    List<Record> userBookList1=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        keyword="△";

        id.add(null);
        id.add(0);
        id.add(1);
        isbn.add("");
        isbn.add("9787221153159");
        isbn.add("9787530219218");
        status.add(2);
        status.add(0);
        status.add(4);
        point.add((float) 4.0);
        point.add((float) -1);
        point.add((float) 4.5);

//        String recordS="{\"id\": \"1\", \"isbn\": \"9787559401076\"}";
//        userBookList.add(JSON.toJavaObject(JSONObject.parseObject(recordS), Record.class));
//        recordS="{\"id\": \"0\", \"isbn\": \"9787559401076\"}";
//        userBookList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
//        recordS="{\"id\": \"1\", \"isbn\": \"9787559401076\"}";
//        userBookList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
//        recordS="{\"id\": \"1\", \"isbn\": \"9787559401076\"}";
//        userBookList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
//        recordS="{\"id\": \"1\", \"isbn\": \"9787559401076\"}";
//        userBookList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
//        recordS="{\"id\": \"1\", \"isbn\": \"9787559401076\"}";
//        userBookList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
//
//        String recordS1="{\"id\": \"1\", \"isbn\": \"9787559401076\",\"point\":\"402\"}";
//        userBookList1.add(JSON.toJavaObject(JSONObject.parseObject(recordS1), Record.class));
//        recordS1="{\"id\": \"0\", \"isbn\": \"9787559401076\",\"point\":\"402\"}";
//        userBookList1.add(JSON.toJavaObject(JSONObject.parseObject(recordS1),Record.class));
//        recordS1="{\"id\": \"1\", \"isbn\": \"9787559401076\",\"point\":\"402\"}";
//        userBookList1.add(JSON.toJavaObject(JSONObject.parseObject(recordS1),Record.class));
//        recordS1="{\"id\": \"1\", \"isbn\": \"9787559401076\",\"point\":\"0\"}";
//        userBookList1.add(JSON.toJavaObject(JSONObject.parseObject(recordS1),Record.class));
//        recordS1="{\"id\": \"1\", \"isbn\": \"9787559401076\",\"point\":\"120\"}";
//        userBookList1.add(JSON.toJavaObject(JSONObject.parseObject(recordS1),Record.class));
//        recordS1="{\"id\": \"1\", \"isbn\": \"9787559401076\",\"point\":\"120\"}";
//        userBookList1.add(JSON.toJavaObject(JSONObject.parseObject(recordS1),Record.class));
    }


    @Test
    public void searchBooks() {
        List<BasicBookInfoDTO> res=bookController.searchBooks(keyword).getBody();
        assertEquals("[]",res.toString());
    }

    @Test
    public void getDetailBookInfo() {
        assertEquals(false,judge1(0,0));
        assertEquals(true,judge1(1,1));
        assertEquals(true,judge1_empty(2,2));
    }

    @Test
    public void getUserBookList() {
        assertEquals(false,judge2(0,0));
        assertEquals(false,judge2(1,1));
        assertEquals(true,judge2_empty(2,2));
    }

    @Test
    public void addBookToReadylist() {
        assertEquals(false,judge3(0,0));
        assertEquals(false,judge3(1,1));
        assertEquals(true,judge3_empty(2,2));
    }

    @Test
    public void finishReadUserbook() {
        assertEquals(false,judge4(0,0,0));
        assertEquals(false,judge4(2,1,1));
        assertEquals(true,judge4_empty(2,2, 2));
    }

    @Test
    public void getUserData() {
        assertEquals(false,judge5(0));
        assertEquals(false,judge5(1));
        assertEquals(true,judge5_empty(2));
    }

}