package com.oneteam.joyread.dao;

import com.oneteam.joyread.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordDAO extends JpaRepository<Record, Integer> {
    List<Record> getAllByUserIdAndIsbn(int userId, String isbn);

    List<Record> getAllByUserId(int userId);

    @Query(value = "select count(*) from (select distinct date from Record) as t", nativeQuery = true)
    Integer getAllDays(int userId);

    @Query(value = "delete from record where id= :id", nativeQuery = true)
    @Modifying
    int deleteById(int id);
}
