package com.banking.bank.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction_details")
public class TransactionsDetails {

    @JsonProperty("transactionDetails")
    @EmbeddedId
    private TransactionId transactionId;

    @Column(name = "transaction_amount")
    private Double transactionAmount;

    @JsonIgnore
    @Column(name = "transaction_flag")
    private String transactionFlag;


}
