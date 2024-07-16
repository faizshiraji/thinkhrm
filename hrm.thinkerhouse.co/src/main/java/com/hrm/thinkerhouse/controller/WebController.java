package com.hrm.thinkerhouse.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrm.thinkerhouse.zkt.Enum.CommandReplyCodeEnum;
import com.hrm.thinkerhouse.zkt.command.AttendanceRecord;
import com.hrm.thinkerhouse.zkt.command.ZKCommandReply;
import com.hrm.thinkerhouse.zkt.exceptions.DeviceNotConnectException;
import com.hrm.thinkerhouse.zkt.terminal.ZKTerminal;

@Controller
public class WebController {

	private final ObjectMapper objectMapper = new ObjectMapper();
	
    
    @GetMapping("/all_attendance")
    public String getAllAttendanceString(Model model) throws IOException, DeviceNotConnectException, ParseException {
        ZKTerminal terminal = new ZKTerminal("192.168.68.200", 4370);
        ZKCommandReply reply = terminal.connect();
        reply = terminal.connectAuth(100);

        CommandReplyCodeEnum code = reply.getCode();
        String productTime = terminal.getProductTime();
        
        List<AttendanceRecord> attendanceRecords = terminal.getAttendanceRecords();
        
        // Convert the list of attendance records to JSON
        String attendanceJson;
        try {
            attendanceJson = objectMapper.writeValueAsString(attendanceRecords);
        } catch (JsonProcessingException e) {
            attendanceJson = "Error processing attendance records";
        }
        
        String message = "Here is our output : " + code + " " + productTime;
        
        // Add the JSON string to the model
        model.addAttribute("message", message);
        model.addAttribute("attendanceJson", attendanceJson);

        return "home";
    }
}