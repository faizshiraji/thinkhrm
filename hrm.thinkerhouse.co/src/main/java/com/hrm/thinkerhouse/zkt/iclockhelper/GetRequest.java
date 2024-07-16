package com.hrm.thinkerhouse.zkt.iclockhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class GetRequest extends ZKRequest {

    private Info info;

    public GetRequest(String sn, String pushVersion, Info info) {
        super(sn, pushVersion);
        this.info = info;
    }

    public static GetRequest fromReq(HttpServletRequest request) throws IOException {
        ParsedRequest parsedReq = ParsedRequest.fromHttpServletRequest(request);
        String[] snVersion = extractSnVersion(parsedReq);
        String sn = snVersion[0];
        String pushVersion = snVersion[1];
        String infoStr = (String) parsedReq.getParams().get("INFO");
        Info info = _fillPlainInfo(infoStr);
        return new GetRequest(sn, pushVersion, info);
    }

    private static Info _fillPlainInfo(String info) {
        if (info != null && !info.isEmpty()) {
            String[] splittedInfo = info.split(",");
            
            if (splittedInfo.length >= 10) {
                String firmwareVersion = splittedInfo[0];
                String userCount = splittedInfo[1];
                String fpCount = splittedInfo[2];
                String transactionCount = splittedInfo[3];
                String ipAddress = splittedInfo[4];
                String fpAlgorithmVersion = splittedInfo[5];
                String faceAlgorithmVersion = splittedInfo[6];
                String requiredFaceCount = splittedInfo[7];
                String enrolledFaceCount = splittedInfo[8];

                String functionSupports = splittedInfo[9];
                boolean fingerprintFunction = functionSupports.charAt(0) == '1';
                boolean faceFunction = functionSupports.charAt(1) == '1';
                boolean userPhotoFunction = functionSupports.charAt(2) == '1';

                info = String.format("FirmwareVersion=%s\tUserCount=%s\tFPCount=%s\tTransactionCount=%s\tIPAddress=%s\tFPAlgorithmVersion=%s\tFaceAlgorithmVersion=%s\tRequiredFaceCount=%s\tEnrolledFaceCount=%s\tFingerprintFunction=%s\tFaceFunction=%s\tUserPhotoFunction=%s",
                        firmwareVersion, userCount, fpCount, transactionCount, ipAddress, fpAlgorithmVersion, faceAlgorithmVersion, requiredFaceCount, enrolledFaceCount, fingerprintFunction, faceFunction, userPhotoFunction);
            }

            return _fillInfo(info);
        }

        return new Info();
    }

    private static Info _fillInfo(String info) {
        Map<String, String> pd = _setValueDict(info);
        Map<String, Object> infoData = new HashMap<>();
        Arrays.stream(Info.class.getDeclaredFields())
                .filter(field -> pd.containsKey(field.getName()))
                .forEach(field -> {
                    String normalKey = field.getName();
                    String value = pd.get(normalKey);

                    if (normalKey.equals("platform") && value.contains("_TFT")) {
                        infoData.put("isTft", true);
                    }

                    if (Arrays.asList("fpCount", "transactionCount", "userCount", "maxFingerCount", "maxAttLogCount").contains(normalKey)) {
                        int intValue = 0;
                        try {
                            intValue = Integer.parseInt(value);
                        } catch (NumberFormatException ignored) {
                        }
                        infoData.put(normalKey, intValue);
                    }

                    if (normalKey.equals("maxAttLogCount")) {
                        infoData.put(normalKey, (int) infoData.getOrDefault(normalKey, 0) * 10000);
                    }

                    if (normalKey.equals("maxFingerCount")) {
                        infoData.put(normalKey, (int) infoData.getOrDefault(normalKey, 0) * 100);
                    }

                    infoData.put(normalKey, value);
                });

        return new Info(infoData);
    }

    private static Map<String, String> _setValueDict(String data) {
        Map<String, String> d = new HashMap<>();
        String v;
        for (String line : data.split("\t")) {
            if (line != null && !line.isEmpty()) {
                v = line.split("\r")[0];
            } else {
                v = line;
            }

            String[] nv = v.split("=", 2);
            if (nv.length > 1) {
                try {
                    String value = nv[1];
                    d.put(nv[0], value);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return d;
    }

    // Replacing RequestUtils.extractSnVersion(parsedReq) method
    private static String[] extractSnVersion(ParsedRequest parsedReq) {
        // Replace this with actual logic to extract SN and version
        return new String[]{"SN123", "Version1"};
    }
}
