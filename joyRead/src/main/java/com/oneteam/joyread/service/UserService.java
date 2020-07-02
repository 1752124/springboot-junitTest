package com.oneteam.joyread.service;

import com.oneteam.joyread.entity.User;
import com.oneteam.joyread.entity.Userbook;

public interface UserService {
    User getByPhone(String phone);

    User getById(int id);

    User get(String phone, String password);

    String getVerifyCode(String id);

    //更新用户名
    void setUserName(int id, String name);

    //更新用户密码
    void setUserPsw(int id, String password);
}
