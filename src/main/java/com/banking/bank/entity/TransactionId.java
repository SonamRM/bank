package com.banking.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TransactionId implements Serializable {
    @JsonIgnore
    @Column(name = "account_no")
    private Integer accountNo;

    @Column(name = "reference_number")
    private Integer referenceNumber;
}
