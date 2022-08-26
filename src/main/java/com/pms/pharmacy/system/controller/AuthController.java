package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.dto.JwtToken;
import com.pms.pharmacy.system.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        if(loginRequest.getUsername() != null && loginRequest.getUsername().equals("dilina")){
            return ResponseEntity.ok(new JwtToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4" +
                    "gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"));
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

}
