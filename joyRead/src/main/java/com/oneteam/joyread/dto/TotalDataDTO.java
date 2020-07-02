package com.oneteam.joyread.dto;

import lombok.Data;

@Data
public class TotalDataDTO {
    private int totalDays;

    private int totalBooks;

    private int totalDone;

    private int totalRecords;

    public TotalDataDTO(int days, int books, int done, int records) {
        this.totalDays = days;
        this.totalBooks = books;
        this.totalDone = done;
        this.totalRecords = records;
    }
}
