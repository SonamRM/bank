package com.banking.bank.service;


import com.banking.bank.dto.*;

public interface BankService {
    CommonResponse registrationUserDetails(RegistrationRequest registrationRequestPOJO);
    CommonResponse loginUser(LoginRequest loginRequest);

    CommonResponse getAccount(String userId);
     CommonResponse fundTransfer(FundTransferDTO fundTransferdto);
     CommonResponse addFund(AddFundDTO addFundDTO);
     CommonResponse checkBalance(int accountNo);
     CommonResponse transactionHistory(int accountNo);



}
