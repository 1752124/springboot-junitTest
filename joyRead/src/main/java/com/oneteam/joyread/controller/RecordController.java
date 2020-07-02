package com.oneteam.joyread.controller;

import com.oneteam.joyread.dto.RecordDTO;
import com.oneteam.joyread.entity.Record;
import com.oneteam.joyread.entity.User;
import com.oneteam.joyread.entity.Userbook;
import com.oneteam.joyread.exception.ServiceException;
import com.oneteam.joyread.service.implementation.BookServiceImpl;
import com.oneteam.joyread.service.implementation.RecordServiceImpl;
import com.oneteam.joyread.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    RecordServiceImpl recordService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BookServiceImpl bookService;

    @CrossOrigin
    @GetMapping(value="/all")
    public ResponseEntity<List<RecordDTO>> getAllUserbookRecord(@RequestParam("id")Integer userId, @RequestParam("isbn")String isbn) {
        try {
            if(userId==null){
                System.out.println("UserID is invalid.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            User user = userService.getById(userId);
            if (user == null) {
                System.out.println("User doesn't exist");
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                List<Record> records = recordService.getAllUserbookRecords(userId, isbn);
                List<RecordDTO> recordDTOS = new ArrayList<RecordDTO>();
                for(int i=0; i<records.size(); i++) {
                    recordDTOS.add(new RecordDTO(records.get(i)));
                }
                return new ResponseEntity<>(recordDTOS, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }


    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<Integer> modifyRecord(@RequestBody Record record) {
        try {
            User user = userService.getById(record.getUserId());
            if (record.getId()==0){
                System.out.println("Add or modify record failed");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            if (user == null) {
                System.out.println("User doesn't exist");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            if(record.getEndPage()<=0 || record.getStartPage()<=0){
                System.out.println("Pages are invalid（0）.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            if (record.getStartPage()>record.getEndPage()){
                System.out.println("Pages are invalid（>）.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            //添加记录
            record.setDate(LocalDate.now());
            int recordId = recordService.addOrUpdate(record);
            if (recordId == 0) {
                System.out.println("Add or modify record failed");
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                //更新current_page,last_time和status
                Userbook userbook = bookService.getUserbook(record.getUserId(), record.getIsbn());
                userbook.setCurrentPage(record.getEndPage());
                userbook.setLastTime(record.getDate());
                userbook.setStatus(3);
                bookService.addOrUpdate(userbook);
                System.out.println("Success!");
                return new ResponseEntity<>(recordId, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @DeleteMapping(value="/delete")
    public ResponseEntity<Integer> deleteRecord(@RequestParam("id")Integer id) {
        try {
            if(id==null){
                System.out.println("RecordID is invalid.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            int record = recordService.deleteById(id);
            return new ResponseEntity<>(record, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
