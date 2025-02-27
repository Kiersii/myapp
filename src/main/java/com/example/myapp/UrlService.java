package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAllByOrderByIdAsc();
    }


    public String createShortUrl(String originalUrl, String suffix) {
        String shortUrl;
        do {
            shortUrl = generateShortUrl(suffix);
        } while (urlRepository.findByShortUrl(shortUrl) != null);

        Url url = new Url();
        url.setLongUrl(originalUrl);
        url.setShortUrl(shortUrl);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        url.setExpirationDate(calendar.getTime());

        urlRepository.save(url);
        return shortUrl;
    }
    public String getOriginalUrl(String shortUrl){
        StringBuilder sb = new StringBuilder();
        sb.append("localhost:8080/");
        Url url = urlRepository.findByShortUrl(String.valueOf(sb.append(shortUrl)));
        if(url != null){
            url.setUrlOpenCount(url.getUrlOpenCount() + 1);
            urlRepository.save(url);
            return url.getLongUrl();
        }

        return null;
    }


    private String generateShortUrl(String suffix){
        if(!Objects.equals(suffix, "")){

            String chars = "0123456789";
            Random random = new Random();
            StringBuilder sb = new StringBuilder();
            sb.append("localhost:8080/").append(suffix);
            for (int i = 0; i < 4; i++) {
                int index = random.nextInt(chars.length());
                sb.append(chars.charAt(index));
            }
            return sb.toString();
        }
    int length = 6;
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    sb.append("localhost:8080/");
    for (int i = 0; i < length; i++) {
        int index = random.nextInt(chars.length());
        sb.append(chars.charAt(index));
    }
    return sb.toString();
    }
    //TODO handle null values
    public void removeFirstUrl() {
         urlRepository.delete(urlRepository.findFirstByOrderByIdAsc());
    }

    public void removeLastUrl() {
        urlRepository.delete(urlRepository.findFirstByOrderByIdDesc());
    }

}
