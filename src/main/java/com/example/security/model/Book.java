package com.example.security.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;

import javax.persistence.*;

@Table
@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
}
