package com.hrm.thinkerhouse.zkt.iclockhelper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetRequestHandler {
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            GetRequest getRequest = GetRequest.fromReq(request);
            System.out.println("Processing GetRequest: " + getRequest);
            sendResponse(response, "Processed GetRequest");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(response, "Error processing GetRequest");
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
