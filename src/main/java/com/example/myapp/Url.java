package com.example.myapp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Entity
public class Url {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    private String longUrl;
    @Getter
    private String shortUrl;
    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Getter
    private Long urlOpenCount = 0L;
    @Getter
    private String category;

}
