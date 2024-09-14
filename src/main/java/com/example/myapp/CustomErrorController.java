package com.example.myapp;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Controller
public class CustomErrorController implements ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error"); // Renders the error.html view
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.addObject("stackTrace", ex.getStackTrace());
        return modelAndView;
    }

    // Map to the error path that Spring will use for its default error handling
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("error"); // Renders the error.html view
        Object status = request.getAttribute("javax.servlet.error.status_code");

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            modelAndView.addObject("statusCode", statusCode);
            modelAndView.addObject("errorMessage", "Error " + statusCode);
        }
        return modelAndView;
    }

    // Since getErrorPath() is deprecated, it is no longer needed.
    // Spring Boot handles the "/error" route internally.
}