package com.oneteam.joyread.controller;

import com.alibaba.fastjson.JSON;
import com.oneteam.joyread.entity.Record;
import com.alibaba.fastjson.JSONObject;
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
public class RecordControllerTest {

    @Autowired
    RecordController recordController;

    List<String>isbn=new ArrayList<>();
    List<Integer> id=new ArrayList<>();

    Boolean judge(int id_index,int isbn_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index)+";isbn:"+isbn.get(isbn_index));
        return recordController.getAllUserbookRecord(id.get(id_index),isbn.get(isbn_index)).hasBody();
    }

    Boolean judge_empty(int id_index,int isbn_index){
        System.out.println("=====================");
        System.out.println("userID:"+id.get(id_index)+";isbn:"+isbn.get(isbn_index));
        String res = recordController.getAllUserbookRecord(id.get(id_index),isbn.get(isbn_index)).getBody().toString();
        return !res.equals("[]");
    }

    List<Record> recordList=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        id.add(null);
        id.add(0);
        id.add(1);
        isbn.add("");
        isbn.add("9787221153159");
        isbn.add("9787530219218");

        String recordS="{\"id\":\"0\",\"userId\": \"1\", \"isbn\": \"9787559401076\",\"startPage\":\"402\",\"endPage\":\"450\"}";
        recordList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
        recordS="{\"id\":\"14\",\"userId\": \"0\", \"isbn\": \"9787559401076\",\"startPage\":\"402\",\"endPage\":\"450\"}";
        recordList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
        recordS="{\"id\":\"14\",\"userId\": \"1\", \"isbn\": \"9787559401076\",\"startPage\":\"402\",\"endPage\":\"450\"}";
        recordList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
        recordS="{\"id\":\"14\",\"userId\": \"1\", \"isbn\": \"9787559401076\",\"startPage\":\"0\",\"endPage\":\"450\"}";
        recordList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
        recordS="{\"id\":\"14\",\"userId\": \"1\", \"isbn\": \"9787559401076\",\"startPage\":\"120\",\"endPage\":\"0\"}";
        recordList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
        recordS="{\"id\":\"14\",\"userId\": \"1\", \"isbn\": \"9787559401076\",\"startPage\":\"120\",\"endPage\":\"100\"}";
        recordList.add(JSON.toJavaObject(JSONObject.parseObject(recordS),Record.class));
    }

    @Test
    public void getAllUserbookRecord() {
        assertEquals(false,judge(0,2));
        assertEquals(false,judge(1,2));
        assertEquals(true,judge_empty(2,2));
        assertEquals(false,judge_empty(2,0));
        assertEquals(false,judge_empty(2,1));
    }

    @Test
    public void modifyRecord() {
        List<Boolean> expected=new ArrayList<>();
        expected.add(false);
        expected.add(false);
        expected.add(true);
        expected.add(false);
        expected.add(false);
        expected.add(false);
        for (int i=0;i<recordList.size();i++){
            System.out.println("===================");
            assertEquals(expected.get(i),recordController.modifyRecord(recordList.get(i)).hasBody());
        }
    }
}