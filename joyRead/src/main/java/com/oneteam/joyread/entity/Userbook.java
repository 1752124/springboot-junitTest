package com.oneteam.joyread.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Userbook")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@IdClass(UserbookPK.class)
public class Userbook implements Serializable {
    @Id
    @Column(name = "id")
    private int userId;

    @Id
    @Column(name = "isbn")
    private String isbn;

    private int status;
    private LocalDate beginTime;
    private LocalDate lastTime;
    private int currentPage;
    private float point;
}
