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
@Table(name = "BooklistWithType")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class BooklistWithType {
    @Id
    @Column(name = "id")
    private int booklistId;

    private String type;
}
