package com.hrm.thinkerhouse.zkt.iclockhelper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeviceMdHandler {
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void sendResponse(HttpServletResponse response, String responseBody) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        response.setContentLength(responseBody.length());
        try (PrintWriter out = response.getWriter()) {
            out.print(responseBody);
        }
    }
}