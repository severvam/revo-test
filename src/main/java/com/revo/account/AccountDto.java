package com.revo.account;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountDto {

	private final String number;
	private final BigDecimal balance;

}
