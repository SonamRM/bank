package com.banking.bank.repository;

import com.banking.bank.entity.AccountDetails;
import com.banking.bank.entity.BankId;
import com.banking.bank.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<AccountDetails, Integer> {
    AccountDetails findByAccountNo(int accountNo);
}
