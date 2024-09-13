package com.example.myapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long>{
    Url findByShortUrl(String shortUrl);
    Url findByLongUrl(String longUrl);
}
