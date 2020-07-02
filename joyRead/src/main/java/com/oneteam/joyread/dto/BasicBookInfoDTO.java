package com.oneteam.joyread.dto;

import com.oneteam.joyread.entity.Book;
import lombok.Data;

@Data
public class BasicBookInfoDTO {
    private String isbn;

    private String name;

    private String pic;

    private String writer;

    public BasicBookInfoDTO(Book book) {
        this.isbn = book.getIsbn();
        this.name = book.getName();
        this.pic = book.getPic();
        this.writer = book.getWriter();
    }
}
