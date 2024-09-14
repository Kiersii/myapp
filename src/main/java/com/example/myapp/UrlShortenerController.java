package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UrlShortenerController {
    @Autowired
    private UrlService urlService;
    @GetMapping("/urlShort")
    public String urlShort() {
        return "urlShort";
    }

    //sprawdzic to postamen
    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("url") String longUrl, Model model) {
        String shortUrl = urlService.createShortUrl(longUrl);
        // Pass both original and short URL to the view
        model.addAttribute("longUrl", longUrl);
        model.addAttribute("shortUrl", shortUrl);
        return "result";  // Returns a page that shows the shortened URL (e.g., result.html)
    }
}
