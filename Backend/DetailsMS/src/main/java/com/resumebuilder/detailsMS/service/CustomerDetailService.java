package com.resumebuilder.detailsMS.service;

import com.resumebuilder.detailsMS.entity.CustomerDetails;
import com.resumebuilder.detailsMS.feign.AuthFeign;
import com.resumebuilder.detailsMS.repository.CustomerRepo;
import com.resumebuilder.detailsMS.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailService {
    @Autowired
    private CustomerRepo repo;

    @Autowired
    private AuthFeign authFeign;

    public ResponseEntity<?> saveDetails(String token, CustomerDetails cus ){
        if(authFeign.isTokenValid(token)){
            if(!existsCustomerDetailsByUsername(authFeign.usernameFromToken(token))){
                cus.setUsername(authFeign.usernameFromToken(token));
                repo.save(cus);
                return ResponseEntity.status(HttpStatus.OK).body(new Response("User Details Uploaded", cus));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("User Already Present", repo.findByUsername(authFeign.usernameFromToken(token))));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("User Already Present",null));
    }
    public ResponseEntity<?> updateCustomer(CustomerDetails cus, String token){
        if(authFeign.isTokenValid(token)){
            if(existsCustomerDetailsByUsername(authFeign.usernameFromToken(token))){
                CustomerDetails existingCustomer = repo.findByUsername(authFeign.usernameFromToken(token));
                existingCustomer.setName(cus.getName());
                existingCustomer.setExp(cus.getExp());
                existingCustomer.setLocation(cus.getLocation());
                existingCustomer.setSummary(cus.getSummary());
                repo.save(existingCustomer);
                return ResponseEntity.status(HttpStatus.OK).body(new Response("User Details Updated", existingCustomer));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new Response("Username not found", cus));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Response("Token Invalid", null));
    }

    public ResponseEntity<?> getCustomerDetailsByUsername(String token){
        if(authFeign.isTokenValid(token)){
            if(!existsCustomerDetailsByUsername(authFeign.usernameFromToken(token))){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response("No User Found", null));
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("Success", repo.findByUsername(authFeign.usernameFromToken(token))));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Token Invalid", null));
    }

    public boolean existsCustomerDetailsByUsername(String username){ return repo.existsCustomerDetailsByUsername(username);}

    public ResponseEntity<?> getSignup(String token) {
        return authFeign.getUserSignup(token);
    }
}
