package com.revo.account;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class AccountDto {

	@NotNull
	private final String number;
	@NotNull
	private final BigDecimal balance;

}
