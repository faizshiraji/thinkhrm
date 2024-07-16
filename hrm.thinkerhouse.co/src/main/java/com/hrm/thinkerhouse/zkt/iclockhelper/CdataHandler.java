package com.hrm.thinkerhouse.zkt.iclockhelper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CdataHandler {
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CdataRequest cdataRequest = CdataRequest.fromReq(request);
            System.out.println("Processing CdataRequest: " + cdataRequest);
            sendResponse(response, "Processed CdataRequest");
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(response, "Error processing CdataRequest");
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