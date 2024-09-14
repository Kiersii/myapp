package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }



    public String createShortUrl(String originalUrl){
        String shortUrl = generateShortUrl();
        Url url = new Url();
        url.setLongUrl(originalUrl);
        url.setShortUrl(shortUrl);
        urlRepository.save(url);
        return shortUrl;
    }
//    public String getOriginalUrl(String shortUrl){
//        Url url = urlRepository.findByShortUrl(shortUrl);
//        return url.getLongUrl();
//    }

    private String generateShortUrl(){
    int length = 6;
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
        int index = random.nextInt(chars.length());
        sb.append(chars.charAt(index));
    }
    return sb.toString();
    }

}
