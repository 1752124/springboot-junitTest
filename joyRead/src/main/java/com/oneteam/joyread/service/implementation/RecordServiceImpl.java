package com.oneteam.joyread.service.implementation;

import com.oneteam.joyread.dao.RecordDAO;
import com.oneteam.joyread.entity.Record;
import com.oneteam.joyread.entity.User;
import com.oneteam.joyread.exception.ServiceException;
import com.oneteam.joyread.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordDAO recordDAO;

    @Autowired
    UserServiceImpl userService;

    @Override
    public List<Record> getAllUserbookRecords(int userId, String isbn) {
        try {
        User user = userService.getById(userId);
        if (user == null) {
            System.out.println("User doesn't exist");
            return null;
        }
        List<Record> recordList=recordDAO.getAllByUserIdAndIsbn(userId, isbn);
        if(recordList==null){
            System.out.println("Isbn is error, or user has no record in this book");
        }
        else{
            System.out.println("Success!");
        }
        return recordList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addOrUpdate(Record record) {
        try {
        User user = userService.getById(record.getUserId());
        if (record.getId()==0){
            System.out.println("Add or modify record failed");
        }

        else if (user == null) {
            System.out.println("User doesn't exist");
        }
        else if(record.getEndPage()<=0 || record.getStartPage()<=0){
            System.out.println("Pages are invalid（0）.");
        }
        else if (record.getStartPage()>record.getEndPage()){
            System.out.println("Pages are invalid（>）.");
        }
        else{
            System.out.println("Success!");
        }
        Record result = recordDAO.save(record);
        return result.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getTotalDays(int id) {
        User user = userService.getById(id);
        if (user == null) {
            System.out.println("User doesn't exist");
            return -1;
        }
        System.out.println("RecordServiceImpl Success!");
        return recordDAO.getAllDays(id);
    }

    @Override
    public int getTotalRecords(int id) {
        User user = userService.getById(id);
        if (user == null) {
            System.out.println("User doesn't exist");
            return -1;
        }
        System.out.println("RecordServiceImpl Success!");
        return recordDAO.getAllByUserId(id).size();
    }

    @Override
    public int deleteById(int id) {
        recordDAO.deleteById(id);
        return id;
    }
}
