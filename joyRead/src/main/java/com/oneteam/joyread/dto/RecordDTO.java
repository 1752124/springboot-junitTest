package com.oneteam.joyread.dto;

import com.oneteam.joyread.entity.Record;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDTO {
    private int id;

    private int startPage;

    private int endPage;

    private String note;

    private LocalDate date;

    public RecordDTO(Record record) {
        this.id = record.getId();
        this.startPage = record.getStartPage();
        this.endPage = record.getEndPage();
        this.note = record.getNote();
        this.date = record.getDate();
    }
}
