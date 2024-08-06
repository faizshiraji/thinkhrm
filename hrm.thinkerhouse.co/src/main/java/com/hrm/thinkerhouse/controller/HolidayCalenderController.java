package com.hrm.thinkerhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrm.thinkerhouse.entities.Calender;
import com.hrm.thinkerhouse.services.CalenderService;

@Controller
public class HolidayCalenderController {

    @Autowired
    private CalenderService calenderService;

    @GetMapping("/holidaycal")
    public String holidayCal(Model model) {
        List<Calender> holidays = calenderService.getCalenders();
        model.addAttribute("holidays", holidays);
        return "admin/holiday";
    }

    @GetMapping("/calendar")
    public String getCalendar(Model model) {
        List<Calender> holidays = calenderService.getCalenders();
        model.addAttribute("holidays", holidays);
        return "admin/holiday";
    }

    @PostMapping("/addHoliday")
    public String addHoliday(Calender calender) {
        calenderService.addCalender(calender);
        return "redirect:/holidaycal";
    }

    @GetMapping("/api/holidays")
    @ResponseBody
    public List<Calender> getHolidays() {
        return calenderService.getCalenders();
    }
}