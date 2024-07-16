package com.hrm.thinkerhouse.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MedCategoryNotFoundException.class)
    public ModelAndView handleMedCategoryNotFoundException(MedCategoryNotFoundException ex, Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/home");
        modelAndView.addObject("msgString", ex.getMessage());
        // Optionally, add any other attributes you want to pass
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(Exception ex, Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("msgString", "An error occurred: " + ex.getMessage());
        // Optionally, add any other attributes you want to pass
        return modelAndView;
    }

    // Remove this duplicate method to avoid ambiguity
    // @ExceptionHandler(Exception.class)
    // public String handleException(Exception e, Model model) {
    //    model.addAttribute("msgString", e.getMessage());
    //    return "error";
    // }
}
