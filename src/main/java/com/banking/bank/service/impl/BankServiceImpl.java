package com.banking.bank.service.impl;

import com.banking.bank.Utils.JwtTokenUtil;
import com.banking.bank.dto.*;
import com.banking.bank.entity.*;
import com.banking.bank.repository.AccountRepository;
import com.banking.bank.repository.BankRepository;
import com.banking.bank.repository.TransactionsRepository;
import com.banking.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    private final AccountRepository accountRepository;

    private final TransactionsRepository transactionsRepository;

    @Override
    public CommonResponse registrationUserDetails(RegistrationRequest registrationRequestPOJO) {
        UserDetails userDetails1 = bankRepository.findByBankIdsUserId(registrationRequestPOJO.getUserId());
        if (userDetails1 == null) {
            BankId bankId = new BankId();

            bankId.setUserId(registrationRequestPOJO.getUserId());
            bankId.setAccountNo(registrationRequestPOJO.getAccountNo());

            UserDetails userRepsonse = bankRepository.save(new UserDetails(bankId, registrationRequestPOJO.getPassword()));

            return new CommonResponse(
                    HttpStatus.OK.value(), userRepsonse, "Registration Succesfuly done");
        }
        return new CommonResponse(HttpStatus.OK.value(), null, "user id already exists");
    }

    @Override
    public CommonResponse loginUser(LoginRequest loginRequest) {
        UserDetails userDetails1 = bankRepository.findByBankIdsUserId(loginRequest.getUserId());
        if (userDetails1 != null) {
            boolean isPasswordCorrect = userDetails1.getPassword().equals(loginRequest.getPassword());
            if (isPasswordCorrect) {
                return new CommonResponse(HttpStatus.OK.value(), JwtTokenUtil.generateToken(loginRequest.getUserId(), loginRequest.getPassword()), "");
            } else {
                return new CommonResponse(HttpStatus.UNAUTHORIZED.value(), null, "Invalid password");
            }
        }
        return new CommonResponse(HttpStatus.UNAUTHORIZED.value(), null, "Invalid credentials");
    }

    @Override
    public CommonResponse getAccount(String userId) {
        UserDetails userDetails1 = bankRepository.findByBankIdsUserId(userId);
        return new CommonResponse(HttpStatus.OK.value(), userDetails1, "");

    }

    @Override
    public CommonResponse fundTransfer(FundTransferDTO fundTransferdto) {
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        int referenceNo = randomDataGenerator.nextInt(1, Integer.MAX_VALUE);


        AccountDetails fromAccountDetails = accountRepository.findByAccountNo(fundTransferdto.getFromAccount());

        AccountDetails toAccountDetails = accountRepository.findByAccountNo(fundTransferdto.getToAccount());
        if (fromAccountDetails != null && toAccountDetails != null) {

            Double availableBalance = fromAccountDetails.getAvailableBalance();
            double amount = fundTransferdto.getAmount();
            if (availableBalance >= amount) {


                double updatedBalance = availableBalance - amount;
                fromAccountDetails.setAvailableBalance(updatedBalance);
                accountRepository.save(fromAccountDetails);
                TransactionsDetails transactionsDetails = this.save(fromAccountDetails.getAccountNo(), amount, "D", referenceNo);

                double toUpdatedBalance = toAccountDetails.getAvailableBalance() + amount;
                toAccountDetails.setAvailableBalance(toUpdatedBalance);
                accountRepository.save(toAccountDetails);
                this.save(toAccountDetails.getAccountNo(), amount, "C", referenceNo);

                return new CommonResponse(200, transactionsDetails, "Fund Transfer successfulLY");
            } else {
                return new CommonResponse(200, null, "Insufficient balance");
            }
        } else {
            return new CommonResponse(200, null, "Account no doesn't exists");

        }


    }

    @Override
    public CommonResponse addFund(AddFundDTO addFundDTO) {
        AccountDetails accountDetails = accountRepository.findByAccountNo(addFundDTO.getAccountNo());
        if (accountDetails != null) {
            double addAmount = accountDetails.getAvailableBalance() + addFundDTO.getAmount();
            accountDetails.setAvailableBalance(addAmount);
            accountRepository.save(accountDetails);
            return new CommonResponse(200, accountDetails, "Fund Added successfully");
        }

        return new CommonResponse(200, null, "Account no doesn't exists");
    }

    @Override
    public CommonResponse checkBalance(int accountNo) {
        AccountDetails accountDetails= accountRepository.findByAccountNo(accountNo);
        return new CommonResponse(200,accountDetails,null);
    }

    @Override
    public CommonResponse transactionHistory(int accountNo) {
        List<TransactionsDetails> transactionsDetails =transactionsRepository.findByTransactionIdAccountNo(accountNo);
        return new CommonResponse(200,transactionsDetails,null);
    }

    public TransactionsDetails save(int accountNo, Double transactionAmount, String transactionFlag, Integer referenceNumber) {
        TransactionsDetails transactionsDetails = new TransactionsDetails();
        TransactionId transactionId = new TransactionId();
        transactionId.setAccountNo(accountNo);
        transactionId.setReferenceNumber(referenceNumber);
        transactionsDetails.setTransactionAmount(transactionAmount);
        transactionsDetails.setTransactionFlag(transactionFlag);
        transactionsDetails.setTransactionId(transactionId);
        transactionsRepository.save(transactionsDetails);
        return transactionsDetails;
    }
}
