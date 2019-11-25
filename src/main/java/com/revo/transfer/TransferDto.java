package com.revo.transfer;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class TransferDto {

	@NotNull
	private final String accountFrom;
	@NotNull
	private final String accountTo;
	@NotNull
	private final BigDecimal amount;

}
