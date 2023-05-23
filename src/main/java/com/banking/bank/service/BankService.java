package com.banking.bank.service;


import com.banking.bank.dto.LoginRequest;
import com.banking.bank.dto.RegistrationRequest;
import com.banking.bank.dto.CommonResponse;

public interface BankService {
    CommonResponse registrationUserDetails(RegistrationRequest registrationRequestPOJO);
    CommonResponse loginUser(LoginRequest loginRequest);

    CommonResponse getAccount(String userId);


}
