package com.oneteam.joyread.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserbookPK implements Serializable {
    private int userId;

    private String isbn;
}
