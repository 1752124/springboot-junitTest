package com.oneteam.joyread.dto;

import com.oneteam.joyread.entity.Book;
import lombok.Data;

@Data
public class BookInfoDTO {
    private String isbn;

    private String name;

    private String pic;

    private String writer;

    private String publisher;

    private String introduction;

    private float percentage;

    private int status;

    public BookInfoDTO(Book book) {
        this.isbn = book.getIsbn();
        this.name = book.getName();
        this.pic = book.getPic();
        this.writer = book.getWriter();
        this.publisher = book.getPublisher();
        this.introduction = book.getIntroduction();
    }
}
