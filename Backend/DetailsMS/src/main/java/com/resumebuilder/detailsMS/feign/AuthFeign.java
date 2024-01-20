package com.resumebuilder.detailsMS.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AuthenticationFeign", url = "http://localhost:1010/users")
public interface AuthFeign {
    @GetMapping("/extract-username")
    String usernameFromToken(@RequestHeader("Authorization") String token);

    @GetMapping("/get")
    ResponseEntity<?> getUserSignup(@RequestHeader("Authorization") String token);

    @GetMapping("/validate-token")
    Boolean isTokenValid(@RequestHeader("Authorization") String token);
}
