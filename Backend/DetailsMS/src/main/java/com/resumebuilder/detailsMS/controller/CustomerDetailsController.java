package com.resumebuilder.detailsMS.controller;

import com.resumebuilder.detailsMS.entity.CustomerDetails;
import com.resumebuilder.detailsMS.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/details")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerDetailsController {

    @Autowired
    private CustomerDetailService customerService;

    @PutMapping("/update")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> updateCustomerDetails(@RequestHeader("Authorization") String token, @RequestBody CustomerDetails cusDetails){
        return customerService.updateCustomer(cusDetails, token);
    }

    @GetMapping("/get-signup")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getSingup(@RequestHeader("Authorization") String token){
        return customerService.getSignup(token);
    }
    @PostMapping("/post")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> addCustomerDetails(@RequestHeader("Authorization") String token, @RequestBody CustomerDetails cusDetails){
        return customerService.saveDetails(token, cusDetails);
    }

    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader("Authorization") String token){
        return customerService.getCustomerDetailsByUsername(token);
    }
}
