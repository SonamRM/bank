package com.banking.bank.service.impl;

import com.banking.bank.Utils.JwtTokenUtil;
import com.banking.bank.dto.CommonResponse;
import com.banking.bank.dto.LoginRequest;
import com.banking.bank.dto.RegistrationRequest;
import com.banking.bank.entity.*;
import com.banking.bank.repository.BankRepository;
import com.banking.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Override
    public CommonResponse registrationUserDetails(RegistrationRequest registrationRequestPOJO) {
        UserDetails userDetails1 = bankRepository.findByBankIdsUserId(registrationRequestPOJO.getUserId());
        if (userDetails1 == null) {
            BankId bankId = new BankId();

            bankId.setUserId(registrationRequestPOJO.getUserId());
            bankId.setAccountNo(registrationRequestPOJO.getAccountNo());

            UserDetails userRepsonse = bankRepository.save(new UserDetails(bankId, registrationRequestPOJO.getPassword()));

            return new CommonResponse(
                    HttpStatus.OK.value(),userRepsonse,"Registration Succesfuly done");
        }
        return new CommonResponse(HttpStatus.OK.value(),null,"user id already exists");
    }

    @Override
    public CommonResponse loginUser(LoginRequest loginRequest) {
        UserDetails userDetails1 = bankRepository.findByBankIdsUserId(loginRequest.getUserId());
        if (userDetails1 != null){
            boolean isPasswordCorrect = userDetails1.getPassword().equals(loginRequest.getPassword());
            if (isPasswordCorrect){
               return new CommonResponse(HttpStatus.OK.value(), JwtTokenUtil.generateToken(loginRequest.getUserId()),"");
            }else {
                return  new CommonResponse(HttpStatus.UNAUTHORIZED.value(), null, "Invalid password");
            }
        }
        return new CommonResponse(HttpStatus.UNAUTHORIZED.value(), null, "Invalid credentials");
    }

    @Override
    public CommonResponse getAccount(String userId) {
        UserDetails userDetails1 = bankRepository.findByBankIdsUserId(userId);
        return new CommonResponse(HttpStatus.OK.value(), userDetails1,"");

    }
}
