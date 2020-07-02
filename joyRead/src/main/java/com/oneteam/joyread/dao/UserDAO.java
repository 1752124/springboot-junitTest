package com.oneteam.joyread.dao;

import com.oneteam.joyread.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserDAO extends JpaRepository<User,Integer> {
    //关键的地方在于方法的名字。findByPhone，是通过username字段查询到对应的行，并返回给User类
    User findByPhone(String phone);
    User findById(int id);
    User getByPhoneAndPassword(String phone, String password);

    @Transactional
    @Modifying
    @Query("update User set name = :name where id =:id")
    void setUserName(@Param("id")int id, @Param("name")String name);

    @Transactional
    @Modifying
    @Query("update User set password = :password where id =:id")
    void setUserPsw(@Param("id")int id, @Param("password")String password);

}
