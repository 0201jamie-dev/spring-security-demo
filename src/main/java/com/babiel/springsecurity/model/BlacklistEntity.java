package com.babiel.springsecurity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "blacklist")
@Getter @Setter
@NoArgsConstructor
public class BlacklistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expiryDate;

    public BlacklistEntity(String token, Date date) {
        this.token = token;
        this.expiryDate = date;
    }
}
