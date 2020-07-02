package com.oneteam.joyread.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import com.alibaba.fastjson.JSON;
import com.oneteam.joyread.controller.RecordController;
import com.oneteam.joyread.entity.Record;
import com.alibaba.fastjson.JSONObject;
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

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class RecordServiceImplTest {

    @Autowired
    RecordService recordService;

    List<String>isbn=new ArrayList<>();
    List<Integer> id=new ArrayList<>();


    List<Record> recordList=new ArrayList<>();
    List<Boolean> expected=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        id.add(1);
        id.add(1);
        id.add(1);

        isbn.add("9787513335782");
        isbn.add("100");
        isbn.add("9787513335782");

        expected.add(false);
        expected.add(false);
        expected.add(true);

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
    public void testGetRecords() {
        for (int i = 0; i < expected.size(); i++) {
            System.out.println("=====================");
            recordService.getAllUserbookRecords(id.get(i), isbn.get(i));
        }
    }

    @Test
    public void modifyRecord() { ;
        for (int i=0;i<recordList.size();i++){
            System.out.println("===================");
            recordService.addOrUpdate(recordList.get(i));
        }
    }
}
