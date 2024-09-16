package com.example.myapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{
    Url findByShortUrl(String shortUrl);
    //Url findByLongUrl(String longUrl);
}
