package com.oneteam.joyread.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BooklistWithBook")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@IdClass(BooklistBook.class)
public class BooklistBook implements Serializable {
    @Id
    @Column(name="id")
    private int booklistId;

    @Id
    private String isbn;
}