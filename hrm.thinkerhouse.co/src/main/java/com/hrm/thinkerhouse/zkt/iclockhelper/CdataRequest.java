package com.hrm.thinkerhouse.zkt.iclockhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hrm.thinkerhouse.zkt.Enum.TableEnum;

public class CdataRequest extends ZKRequest {
    private final String method;
    private final String pin;
    private final boolean save;
    private final String body;
    private final String stamp;
    private final String operationStamp;
    private final TableEnum table;
    private final AttendanceLog attendanceLog;
    private final OperationLog operationLog;
    private final AttendancePhotoLog attendancePhotoLog;
    private final String options;
    private final String info;

    public CdataRequest(String sn, String pushVersion, String method, String pin, boolean save, String body, 
                        String stamp, String operationStamp, TableEnum table, AttendanceLog attendanceLog, 
                        OperationLog operationLog, AttendancePhotoLog attendancePhotoLog, String options) {
        super(sn, pushVersion);
        this.method = method;
        this.pin = pin;
        this.save = save;
        this.body = body;
        this.stamp = stamp;
        this.operationStamp = operationStamp;
        this.table = table;
        this.attendanceLog = attendanceLog;
        this.operationLog = operationLog;
        this.attendancePhotoLog = attendancePhotoLog;
        this.options = options;
        this.info = "";
    }

    public String getMethod() {
        return method;
    }

    public String getPin() {
        return pin;
    }

    public boolean isSave() {
        return save;
    }

    public String getBody() {
        return body;
    }

    public String getStamp() {
        return stamp;
    }

    public String getOperationStamp() {
        return operationStamp;
    }

    public TableEnum getTable() {
        return table;
    }

    public AttendanceLog getAttendanceLog() {
        return attendanceLog;
    }

    public OperationLog getOperationLog() {
        return operationLog;
    }

    public AttendancePhotoLog getAttendancePhotoLog() {
        return attendancePhotoLog;
    }

    public String getOptions() {
        return options;
    }

    public String getInfo() {
        return info;
    }

    public static CdataRequest fromReq(HttpServletRequest request) throws IOException, IllegalAccessException, InstantiationException, URISyntaxException {
        ParsedRequest parsedReq = ParsedRequest.fromHttpServletRequest(request);
        String requestMethod = parsedReq.getMethod();
        String query = request.getQueryString();
        String[] sn = extractSnVersion(parsedReq);

        Map<String, Object> uriParams = parseQueryParameters(query);

        if (uriParams == null) {
            return new CdataRequest(sn[0], sn[1], requestMethod, "", false, "", "", "", TableEnum.UNKNOWN, null, null, null, null);
        }

        if ("GET".equalsIgnoreCase(requestMethod)) {
            return new CdataRequest(sn[0], sn[1], requestMethod, "", true, "", "", "", TableEnum.UNKNOWN, null, null, null, null);
        }

        if ("POST".equalsIgnoreCase(requestMethod)) {
            String stamp = getStringOrNull(uriParams, "Stamp");
            String operationStamp = getStringOrNull(uriParams, "OpStamp");
            TableEnum table = getTableEnumOrNull(uriParams, "table");

            AttendanceLog attLog = null;
            OperationLog operLog = null;
            AttendancePhotoLog attPhotoLog = null;

            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            if (table == TableEnum.OPERLOG) {
                operLog = OperationLog.fromStr(br);
            }

            if (table == TableEnum.ATTLOG) {
                // attLog = AttendanceLog.fromStr(br);
            }

            if (table == TableEnum.ATTPHOTO) {
                // attPhotoLog = AttendancePhotoLog.fromRequestPin(parsedReq.getParams(), parsedReq.getBody().toString());
            }

            return new CdataRequest(sn[0], sn[1], requestMethod, "", false, parsedReq.getBody().toString(), 
                                    stamp, operationStamp, table, attLog, operLog, attPhotoLog, null);
        }

        return new CdataRequest(sn[0], sn[1], requestMethod, "", false, "", "", "", TableEnum.UNKNOWN, null, null, null, null);
    }

    // Helper method to get a string or return an empty string if null
    private static String getStringOrNull(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value != null ? value.toString() : "";
    }

    // Helper method to get a TableEnum or return UNKNOWN if null or invalid
    private static TableEnum getTableEnumOrNull(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value != null) {
            try {
                return TableEnum.valueOf(value.toString());
            } catch (IllegalArgumentException e) {
                // Handle invalid enum value
            }
        }
        return TableEnum.UNKNOWN;
    }

    // Helper method to extract SN version from ParsedRequest
    private static String[] extractSnVersion(ParsedRequest parsedReq) {
        // Replace this with your actual logic to extract SN and version
        return new String[]{"SN123", "Version1"};
    }

    // Parse query parameters into a map
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
