package com.hrm.thinkerhouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.thinkerhouse.zkt.iclockhelper.CdataRequest;
import com.hrm.thinkerhouse.zkt.iclockhelper.GetRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/iclock")
public class ZKTecoController {

    @GetMapping("/cdata.aspx")
    public void handleCdata(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CdataRequest cdataRequest = CdataRequest.fromReq(request);
            System.out.println("Processing CdataRequest: " + cdataRequest);
            sendResponse(response, "Processed CdataRequest");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(response, "Error processing CdataRequest");
        }
    }

    @GetMapping("/getrequest.aspx")
    public void handleGetRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            GetRequest getRequest = GetRequest.fromReq(request);
            System.out.println("Processing GetRequest: " + getRequest);
            sendResponse(response, "Processed GetRequest");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(response, "Error processing GetRequest");
        }
    }

    @PostMapping("/devicemd.aspx")
    public void handleDeviceMd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // DeviceMdRequest deviceMdRequest = DeviceMdRequest.fromReq(request);
            // System.out.println("Processing DeviceMdRequest: " + deviceMdRequest);
            // Add your logic to process DeviceMdRequest
            sendResponse(response, "Processed DeviceMdRequest");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(response, "Error processing DeviceMdRequest");
        }
    }

    @PostMapping("/edata.aspx")
    public void handleEdata(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // EdataRequest edataRequest = EdataRequest.fromReq(request);
            // System.out.println("Processing EdataRequest: " + edataRequest);
            // Add your logic to process EdataRequest
            sendResponse(response, "Processed EdataRequest");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(response, "Error processing EdataRequest");
        }
    }

    private void sendResponse(HttpServletResponse response, String responseBody) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        response.setContentLength(responseBody.length());
        response.getWriter().print(responseBody);
    }
}