package com.oneteam.joyread.dao;

import com.oneteam.joyread.entity.Userbook;
import com.oneteam.joyread.entity.UserbookPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserbookDAO extends JpaRepository<Userbook, UserbookPK> {
    //找到用户图书记录
    Userbook findAllByUserIdAndIsbn(int userId, String isbn);

    //找到用户所有图书
    List<Userbook> findAllByUserId(int userId);

    //找到某状态的用户图书记录
    List<Userbook> findAllByUserIdAndStatus(int userId, int status);
}
