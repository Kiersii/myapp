package com.example.myapp;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class UrlShortenerController {
    @Autowired
    private UrlService urlService;
    @GetMapping("/urlShort")
    public String urlShort() {
        return "urlShort";
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody @Valid UrlRequest urlRequest) {
        String shortUrl = urlService.createShortUrl(urlRequest.getUrl());
        System.out.println(urlRequest.getUrl());
        System.out.println(shortUrl);
        return ResponseEntity.ok(new UrlResponse(shortUrl));
    }
//    @PostMapping("/shorten") method with query parameter returns view with result
//    public String shortenUrl(@RequestParam("url") @Pattern(regexp = "^(https?:\\/\\/)?([\\da-z.-]+)\\.([a-z.]{2,6})([\\/\\w .-]*)*\\/?$") String longUrl, Model model) {
//        String shortUrl = urlService.createShortUrl(longUrl);
//        // Pass both original and short URL to the view
//        model.addAttribute("longUrl", longUrl);
//        model.addAttribute("shortUrl", shortUrl);
//        return "result";  // Returns a page that shows the shortened URL (e.g., result.html)
//    }

    @GetMapping("/{shortUrl}")
    public void redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        if (originalUrl != null) {
            response.sendRedirect(originalUrl);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

