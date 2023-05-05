package com.banking.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegrasationsResponsePOJO {
    public boolean responseStatus;
    public String statusMessage;
    public String userId;
    public int accountNo;
}
