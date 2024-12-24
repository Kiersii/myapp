package com.example.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UrlListController {
    @Autowired
    private UrlService urlService;
    @PostMapping("/removeFirstUrl")
    public String  removeFirstUrl() {
        urlService.removeFirstUrl();
        return "redirect:/listUrls";
    }

    @PostMapping("/removeLastUrl")
    public String removeLastUrl() {
        urlService.removeLastUrl();
        return "redirect:/listUrls";
    }

//    @GetMapping("/listUrls") changed Controller to RestController, view may not work as expected
//    public String getAllUrls(Model model){
//        model.addAttribute("urls", urlService.getAllUrls());
//        return "listUrls";
//    }

    @GetMapping("/api/urls")
    public List<Url> getAllUrls() {
        return urlService.getAllUrls();
    }
}
