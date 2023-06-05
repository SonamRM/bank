package com.banking.bank.controller;

import com.banking.bank.Utils.JwtTokenUtil;
import com.banking.bank.dto.*;
import com.banking.bank.repository.BankRepository;
import com.banking.bank.service.BankService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class BankController {
    private final BankService bankService;
    private final BankRepository bankRepository;


    @PostMapping(path = "/registeruserdetails")
    public ResponseEntity<CommonResponse> registerUserDetails(@RequestBody RegistrationRequest registrationRequestPOJO) {
        CommonResponse commonResponse = bankService.registrationUserDetails(registrationRequestPOJO);

        return ResponseEntity.ok().body(commonResponse);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<CommonResponse> loginUserDetails(@RequestBody LoginRequest loginRequest) {
        CommonResponse commonResponse = bankService.loginUser(loginRequest);
        return ResponseEntity.ok().body(commonResponse);
    }

    @GetMapping(path = "/api/getAccount/{userId}")
    public ResponseEntity<CommonResponse> getAccount(@PathVariable String userId) {
        CommonResponse commonResponse = bankService.getAccount(userId);

        return ResponseEntity.ok().body(commonResponse);
    }

    @PostMapping(path = "/api/fund-transfer")
    public ResponseEntity<CommonResponse> fundTransfer(@RequestBody FundTransferDTO fundTransferdto, HttpServletRequest httpServletRequest) {
        if (verifyToken(fundTransferdto.getFromAccount(), httpServletRequest)) {
            CommonResponse commonResponse = bankService.fundTransfer(fundTransferdto);
            return ResponseEntity.ok().body(commonResponse);
        }
        return ResponseEntity.ok().body(new CommonResponse(401, null, "Invalid token, please try again"));

    }

    @PostMapping(path = "/api/add-fund")
    public ResponseEntity<CommonResponse> addFund(@RequestBody AddFundDTO addFundDTO) {
        CommonResponse commonResponse = bankService.addFund(addFundDTO);
        return ResponseEntity.ok().body(commonResponse);
    }


    @GetMapping(path = "/api/check-balance/{accountNo}")
    public ResponseEntity<CommonResponse> checkBalance(@PathVariable int accountNo, HttpServletRequest httpServletRequest) {
        if (verifyToken(accountNo, httpServletRequest)) {
            CommonResponse commonResponse = bankService.checkBalance(accountNo);
            return ResponseEntity.ok().body(commonResponse);
        }
        return ResponseEntity.ok().body(new CommonResponse(401, null, "Invalid token, please try again"));

    }

    @GetMapping(path = "/api/transaction-history/{accountNo}")
    public ResponseEntity<CommonResponse> transactionhistory(@PathVariable int accountNo, HttpServletRequest httpServletRequest) {
        if (verifyToken(accountNo, httpServletRequest)) {
            CommonResponse commonResponse = bankService.transactionHistory(accountNo);
            return ResponseEntity.ok().body(commonResponse);
        }
        return ResponseEntity.ok().body(new CommonResponse(401, null, "Invalid token, please try again"));
    }

    public Boolean verifyToken(int accountNo, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        boolean check;
        try {
            check = JwtTokenUtil.verify(authorization, bankRepository, accountNo);
        } catch (Exception e) {
            check = false;
        }
        return check;
    }
}