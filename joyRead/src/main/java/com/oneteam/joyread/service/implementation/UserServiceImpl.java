package com.oneteam.joyread.service.implementation;

import com.oneteam.joyread.dao.UserDAO;
import com.oneteam.joyread.entity.User;
import com.oneteam.joyread.exception.ServiceException;
import com.oneteam.joyread.service.UserService;
import com.oneteam.joyread.utils.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String phone) {
        User user = getByPhone(phone);
        return null!=user;
    }

    @Override
    public User getByPhone(String phone) {
        return userDAO.findByPhone(phone);
    }

    @Override
    public User getById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User get(String phone, String password) {
        User phoneActual = getByPhone(phone);
        if (phoneActual == null) {
            System.out.println("Phone doesn't exist");
            return null;
        } else {
            User user=userDAO.getByPhoneAndPassword(phone, password);
            if(user==null){
                System.out.println("Password is error");
            }
            else{
                System.out.println("Success!");
            }
            return user;
        }
    }

    @Override
    public String getVerifyCode(String id) {
        User user = getByPhone(id);
        if(user != null) {
            throw new ServiceException("用户已注册", HttpStatus.CONFLICT);
        }
        String randomCode = SendMessageUtil.sendMessage(id);
        return randomCode;
    }

    public void add(User user) {
        userDAO.save(user);
    }

    //更新用户名
    @Override
    public void setUserName(int id, String name){
        userDAO.setUserName(id, name);
    };

    //更新用户密码
    @Override
    public void setUserPsw(int id, String password){
        userDAO.setUserPsw(id, password);
    };
}
