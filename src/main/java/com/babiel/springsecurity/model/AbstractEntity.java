package com.babiel.springsecurity.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jamie Augustin
 * @version 1.0
 */
@MappedSuperclass
@Getter @Setter
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}