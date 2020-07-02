package com.oneteam.joyread.controller;

import com.oneteam.joyread.dao.UserDAO;
import com.oneteam.joyread.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: piaoxue
 * @create: 2020-06-26 19:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserDAO userDao; //注入dao接口

    @Autowired
    private UserController userController;

    List<String> phone = new ArrayList<>();
    List<String> password = new ArrayList<>();
    List<Boolean> resultList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        phone.add("100");//不存在的phone
        phone.add("18019050039");
        phone.add("18019050039");

        password.add("123456");
        password.add("100");//不存在的password
        password.add("123456");

        resultList.add(false);
        resultList.add(false);
        resultList.add(true);
    }

    @Test
    public void testLogin() {
        System.out.println("测试开始");
        //检测是否能get到userInfo
        for (int i = 0; i < resultList.size(); i++) {
            System.out.println("===================");
            userController.login(phone.get(i), password.get(i));
        }
    }

}

