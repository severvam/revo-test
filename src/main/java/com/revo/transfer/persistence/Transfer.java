package com.revo.transfer.persistence;

import com.revo.core.persistence.Persistable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = Transfer.TABLE_NAME)
public class Transfer implements Persistable<Long> {

	public static final String TABLE_NAME = "transfer";

	@Getter(AccessLevel.NONE)
	private Long id;

	private Long accountFromId;

	private Long accountToId;

	private Date transactionDate;

	private BigDecimal amount;

	public Transfer(Long accountFromId, Long accountToId, Date transactionDate, BigDecimal amount) {
		this.accountFromId = accountFromId;
		this.accountToId = accountToId;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}

	@Id
	@GeneratedValue
	@Override
	public Long getId() {
		return id;
	}

	@Column(name = "account_from_id")
	public Long getAccountFromId() {
		return accountFromId;
	}

	@Column(name = "account_to_id")
	public Long getAccountToId() {
		return accountToId;
	}

	@Column(name = "transaction_date")
	public Date getTransactionDate() {
		return transactionDate;
	}

}
