package com.raunak.springbootdemootp.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/customer/")
public class CustomerController {
    private static final Logger log = LogManager.getLogger(CustomerController.class);
    @Autowired private CustomerRepo customerRepo;

    @GetMapping()
    public List<Customer> get(){
        /**
         @URL - http://localhost:9500/auth/customer/
         */
        log.info("Retrieving Customer objects");
        return customerRepo.findAll();
    }

    @PostMapping()
    public Map<String, Object> post(@RequestBody Customer reqBody){
        /**
         @URL - http://localhost:9500/auth/customer/
         @RequestBody -
         {
             "email": "user-email-id",
             "firstName": "Raunak",
             "enabled": "True"
         }
         @returns -

         */
        log.info("Creating new object for Customer - {}", reqBody.toString());
        HashMap<String, Object> payload = new HashMap<>();

        try {
            Customer newObj = customerRepo.save(reqBody);
            log.info("New Customer object created - {}", newObj.toString());
            payload.put("customer", newObj);
        }
        catch (Exception e){
            log.error(e.getMessage());
            payload.put("error", e.getMessage());
        }

        return payload;
    }


}
