package com.banking.bank.repository;

import com.banking.bank.entity.BankId;
import com.banking.bank.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<UserDetails , BankId> {
    UserDetails findByBankIdsUserId(String userId);
}
