package com.banking.bank.service;


import com.banking.bank.entity.RegistrationRequestPOJO;
import com.banking.bank.entity.RegrasationsResponsePOJO;
import com.banking.bank.entity.UserDetails;

import java.util.List;

public interface BankService {
    RegrasationsResponsePOJO registrationUserDetails(RegistrationRequestPOJO registrationRequestPOJO);

}
