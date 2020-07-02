package com.oneteam.joyread.dto;

import com.oneteam.joyread.entity.Booklist;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BooklistDTO {
    private int id;

    private String name;

    private String introduction;

    private LocalDate time;

    private List<BasicBookInfoDTO> books;

    public BooklistDTO(Booklist booklist) {
        this.id = booklist.getId();
        this.name = booklist.getName();
        this.introduction = booklist.getIntroduction();
        this.time = booklist.getTime();
    }
}
