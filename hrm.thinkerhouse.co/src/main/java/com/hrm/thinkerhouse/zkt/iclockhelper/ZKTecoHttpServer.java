package com.hrm.thinkerhouse.zkt.iclockhelper;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;



@WebServlet("/*")
public class ZKTecoHttpServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestUri = request.getRequestURI();
        String query = request.getQueryString();

        System.out.println("Received " + request.getMethod() + " request: " + requestUri);
        System.out.println("Query parameters: " + query);

        if (requestUri.startsWith("/iclock/cdata.aspx")) {
            new CdataHandler().handle(request, response);
        } else if (requestUri.startsWith("/iclock/getrequest.aspx")) {
            new GetRequestHandler().handle(request, response);
        } else if (requestUri.startsWith("/iclock/devicemd.aspx")) {
            new DeviceMdHandler().handle(request, response);
        } else if (requestUri.startsWith("/iclock/edata.aspx")) {
            new EdataHandler().handle(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
