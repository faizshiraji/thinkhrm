package com.hrm.thinkerhouse.zkt.iclockhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ParsedRequest {
    private final HttpServletRequest request;
    private final String method;
    private final URI uri;
    private final Map<String, Object> params;
    private final String body;

    public ParsedRequest(HttpServletRequest request, String method, URI uri, Map<String, Object> params, String body) {
        this.request = request;
        this.method = method;
        this.uri = uri;
        this.params = params;
        this.body = body;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public String getMethod() {
        return method;
    }

    public URI getUri() {
        return uri;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getBody() {
        return body;
    }

    public String getQuery() {
        return uri.getQuery();
    }

    public static ParsedRequest fromHttpServletRequest(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        URI uri;
        try {
            uri = new URI(request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URI", e);
        }

        Map<String, Object> params = parseQueryParameters(request.getQueryString());

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        return new ParsedRequest(request, method, uri, params, body.toString());
    }

    private static Map<String, Object> parseQueryParameters(String query) {
        Map<String, Object> params = new HashMap<>();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String key = keyValue[0];
                String value = keyValue.length > 1 ? keyValue[1] : "";
                params.put(key, value);
            }
        }
        return params;
    }
}
