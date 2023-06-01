package com.banking.bank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "account_details")
public class AccountDetails {

    @Id
    @Column(name = "account_no")
    private Integer accountNo;

    @Column(name = "available_balance")
    private Double availableBalance;


}
