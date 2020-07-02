package com.oneteam.joyread.dto;

import com.oneteam.joyread.entity.Book;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserListDTO {
    private String isbn;

    private String name;

    private String pic;

    private String writer;

    private LocalDate beginTime;

    private float percentage;

    private LocalDate lastTime;

    private float point;

    public UserListDTO(Book book){
        this.isbn = book.getIsbn();
        this.name = book.getName();
        this.pic = book.getPic();
        this.writer = book.getWriter();
    }
}
