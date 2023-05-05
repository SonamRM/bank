package com.banking.bank.service.impl;

import com.banking.bank.entity.BankId;
import com.banking.bank.entity.RegistrationRequestPOJO;
import com.banking.bank.entity.RegrasationsResponsePOJO;
import com.banking.bank.entity.UserDetails;
import com.banking.bank.repository.BankRepository;
import com.banking.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Override
    public RegrasationsResponsePOJO registrationUserDetails(RegistrationRequestPOJO registrationRequestPOJO) {


        UserDetails userDetails1 = bankRepository.findByBankIdsUserId(registrationRequestPOJO.getUserId());


        if (userDetails1 == null) {
            BankId bankId = new BankId();
            bankId.setUserId(registrationRequestPOJO.getUserId());
            bankId.setAccountNo(registrationRequestPOJO.getAccountNo());
            UserDetails userRepsonse = bankRepository.save(new UserDetails(bankId, registrationRequestPOJO.getPassword()));
            return new RegrasationsResponsePOJO(
                    true,
                    "Registration Successfully completed",
                    userRepsonse.getBankIds().getUserId(),
                    userRepsonse.getBankIds().getAccountNo());
        }
        return new RegrasationsResponsePOJO(
                false,
                "User is already exist",
                null,
                0
        );
    }
}
