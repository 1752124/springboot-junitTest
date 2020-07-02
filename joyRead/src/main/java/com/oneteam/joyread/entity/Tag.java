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
@Table(name = "Tag")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Tag {
    @Id
    @Column(name="type")
    private String type;
}