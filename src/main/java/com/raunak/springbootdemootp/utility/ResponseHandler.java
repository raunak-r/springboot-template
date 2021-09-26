package com.raunak.springbootdemootp.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;

public class ResponseHandler {
    private static final Logger log = LogManager.getLogger(ResponseHandler.class);

    public static HashMap<String, Object> createPayload(Object data, Integer code, String error, Boolean printFullLogs){
        /**
         *
         Returns a json response in the following format.
             response = {
                 "meta": {  // Mandatory Field
                    "code": code,
                    "error": msg,     // If code is not 200, code and error will be present.
                 },
                 "data": data,  // will be present only for status code 200.
             }

         USAGE -
             In case of success, and json payloads.
                -> return ResponseHandler.createPayload(payload, 200, null, true);
             OR in case of errors.
                -> return ResponseHandler.createPayload(null, 400, error, true);
         */
        HashMap<String, Object> payload = new HashMap<>();
        HashMap<String, Object> meta = new HashMap<>();

        // Mandatory Fields
        meta.put("code", code);

        if (code != 200){
            meta.put("error", error);
        }

        if (data != null){
            payload.put("data", data);
        }

        payload.put("meta", meta);

        if (printFullLogs == true) {
            log.info("Returning payload - " + Arrays.asList(payload));
        }
        return payload;
    }
}
