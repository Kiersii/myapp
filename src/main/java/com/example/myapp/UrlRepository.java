package com.example.myapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{
    Url findByShortUrl(String shortUrl);
    Url findFirstByOrderByIdAsc();
    Url findFirstByOrderByIdDesc();

    List<Url> findAllByOrderByIdAsc();
}
