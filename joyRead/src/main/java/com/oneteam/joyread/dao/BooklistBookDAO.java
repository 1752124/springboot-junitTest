package com.oneteam.joyread.dao;

import com.oneteam.joyread.entity.BooklistBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooklistBookDAO extends JpaRepository<BooklistBook, Integer> {
    List<BooklistBook> findByBooklistId(int id);
}
