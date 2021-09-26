package com.raunak.springbootdemootp.auth;

import com.raunak.springbootdemootp.utility.ResponseHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth/")
public class LoginController {
    private static final Logger log = LogManager.getLogger(LoginController.class);

    @Autowired private CustomerRepo customerRepo;
    @Autowired private OtpService otpService;

    @PostMapping("/get-otp/")
    public HashMap<String, Object> getOtp(@RequestBody HashMap<String, Object> reqBody){
        /**
         Method that will take only an email id in the input.
         And send an otp to that email.

         @URL - localhost:9500/auth/get-otp/
         @RequestBody -
         {
            "email": "user-email-id"
         }
         @returns -

         */
        log.info("Received new request to generate otp");
        HashMap<String, Object> payload = new HashMap<>();

        try {
            Customer customer = customerRepo.findByEmail((String) reqBody.get("email"));
//            log.info("Customer object retrieved - {}", ReflectionToStringBuilder.toString(customer));
            log.info("Customer object retrieved - {}", customer.toString());
            otpService.generateOneTimePassword(customer);

            payload.put("success", true);
            return ResponseHandler.createPayload(payload, 200, null, true);
        }
        catch (Exception e){
            String error = e.getMessage();
            return ResponseHandler.createPayload(null, 400, error, true);
        }

    }

    @PostMapping("/validate-otp/")
    public HashMap<String, Object> validateOtp(@RequestBody HashMap<String, String> reqBody){
        /**
         @URL - localhost:9500/auth/validate-otp/
         @RequestBody -
         {
             "email": "user-email-id",
             "otp": "KcFt9DXV"
         }
         @returns -

         */
        log.info("Received new request to validate otp");
        HashMap<String, Object> payload = new HashMap<>();

        try {
            payload.put("isOtpValid", otpService.validateOTP(reqBody.get("email"), reqBody.get("otp")));
            return ResponseHandler.createPayload(payload, 200, null, true);
        }
        catch (Exception e){
            String error = e.getMessage();
            return ResponseHandler.createPayload(null, 400, error, true);
        }
    }
}
