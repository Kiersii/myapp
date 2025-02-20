package com.example.myapp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UrlRequest {
    @NotBlank(message = "URL is mandatory")
    @Pattern(regexp = "^(https?:\\/\\/)?([\\da-z.-]+)\\.([a-z.]{2,6})([\\/\\w .-]*)*\\/?$", message = "Invalid URL")
//    @Pattern(regexp = "^(https?:\\\\/\\\\/)?([\\\\da-z.-]+)\\\\.([a-z.]{2,6})([\\\\/\\\\w .-]*)*\\\\/?$", message = "Invalid URL")
    private String url;

    private String suffix;

    public String getSuffix() {
        return suffix != null ? suffix : "";
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
