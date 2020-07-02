package com.oneteam.joyread.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BookWithType")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class BookWithType {
    @Id
    @Column(name = "isbn")
    private String isbn;

    private String type;
}
