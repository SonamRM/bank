package com.banking.bank.repository;

import com.banking.bank.entity.BankId;
import com.banking.bank.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<UserDetails , BankId> {
    @Query(value = "SELECT * FROM user_details where user_id=:userId ", nativeQuery = true)
    UserDetails  findByBankIdsUserId(@Param("userId") String userId);

    @Query(value = "SELECT count(*) FROM user_details where user_id=:userId and account_no=:accountNo", nativeQuery = true)
    Integer findByAccountNoAndUserId(@Param("userId") String userId, @Param("accountNo") int accountNo);


}
