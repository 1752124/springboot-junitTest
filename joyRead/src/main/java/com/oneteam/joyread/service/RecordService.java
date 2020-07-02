package com.oneteam.joyread.service;

import com.oneteam.joyread.entity.Record;

import java.util.List;

public interface RecordService {

    //获取用户某图书的所有图书记录
    public List<Record> getAllUserbookRecords(int userId, String isbn);

    //添加或修改阅读记录
    public Integer addOrUpdate(Record record);

    //阅读天数
    public int getTotalDays(int id);

    //总进度记录条数
    public int getTotalRecords(int id);

    //删除记录
    public int deleteById(int id);
}
