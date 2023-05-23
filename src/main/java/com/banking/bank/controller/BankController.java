package com.banking.bank.controller;

import com.banking.bank.dto.LoginRequest;
import com.banking.bank.dto.RegistrationRequest;
import com.banking.bank.dto.CommonResponse;
import com.banking.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping(path = "/registeruserdetails")
    public ResponseEntity<CommonResponse> registerUserDetails(@RequestBody RegistrationRequest registrationRequestPOJO) {
        CommonResponse commonResponse = bankService.registrationUserDetails(registrationRequestPOJO);

        return ResponseEntity.ok().body(commonResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<CommonResponse> loginUserDetails(@RequestBody LoginRequest loginRequest) {
        CommonResponse commonResponse  = bankService.loginUser(loginRequest);

        return ResponseEntity.ok().body(commonResponse);
    }

    @GetMapping(path = "/api/getAccount/{userId}")
    public ResponseEntity<CommonResponse> getAccount(@PathVariable String userId) {
        CommonResponse commonResponse  = bankService.getAccount(userId);

        return ResponseEntity.ok().body(commonResponse);
    }
}


