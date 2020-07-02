package com.oneteam.joyread.controller;

import com.oneteam.joyread.dto.LoginDTO;
import com.oneteam.joyread.dto.TotalDataDTO;
import com.oneteam.joyread.entity.Book;
import com.oneteam.joyread.entity.User;
import com.oneteam.joyread.entity.Userbook;
import com.oneteam.joyread.exception.ServiceException;
import com.oneteam.joyread.service.implementation.BookServiceImpl;
import com.oneteam.joyread.service.implementation.RecordServiceImpl;
import com.oneteam.joyread.service.implementation.UserServiceImpl;
import com.oneteam.joyread.utils.JWTUtil;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    RecordServiceImpl recordService;

    @CrossOrigin
    @PostMapping(value="/login")
    public ResponseEntity<LoginDTO> login(@RequestParam("phone")String phone, @RequestParam("password")String password) {
        try {
            User user = userService.get(phone, password);
            if(user == null) {
                throw new UnauthenticatedException("phone or password incorrect!");
            } else {
                LoginDTO loginDTO = new LoginDTO(user);
                loginDTO.setAuthorization(JWTUtil.sign(phone, password));
                return new ResponseEntity<>(loginDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value="/verification/{phone}")
    public ResponseEntity<String> getVerificationCode(@PathVariable("phone")String phone) {
        try {
            return new ResponseEntity<>("123456", HttpStatus.OK);
            //return new ResponseEntity<>(userService.getVerifyCode(phone), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @GetMapping(value="/totaldata/{id}")
    public ResponseEntity<TotalDataDTO> getUserData(@PathVariable("id")Integer id) {
        try {
            if(id==null){
                System.out.println("UserID is invalid.");
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
            User user = userService.getById(id);
            if (user == null) {
                throw new ServiceException("user doesn't exist");
            } else {
                int days = recordService.getTotalDays(id);
                int books = bookService.getAll(id).size();
                int done = bookService.getAllUserbook(id, 4).size();
                int records = recordService.getTotalRecords(id);
                TotalDataDTO totalDataDTO = new TotalDataDTO(days, books, done, records);
                return new ResponseEntity<>(totalDataDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PutMapping(value="/change_name")
    ResponseEntity<Integer> changeName(@RequestParam("id")Integer id, @RequestParam("name")String name) {
        try {
            User user = userService.getById(id);
            if (user == null) {
                throw new ServiceException("user doesn't exist");
            } else {
                userService.setUserName(id, name);
                return new ResponseEntity<>(user.getStatus(), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PutMapping(value="/change_password")
    ResponseEntity<Integer> changePassword(@RequestParam("id")Integer id, @RequestParam("password")String password) {
        try {
            User user = userService.getById(id);
            if (user == null) {
                throw new ServiceException("user doesn't exist");
            } else {
                userService.setUserPsw(id, password);
                return new ResponseEntity<>(user.getStatus(), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
