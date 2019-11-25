package com.revo.account.persistence;

import com.revo.core.persistence.Persistable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = Account.TABLE_NAME)
public class Account implements Persistable<Long> {

	public static final String TABLE_NAME = "account";

	@Getter(AccessLevel.NONE)
	private Long id;

	@Getter(AccessLevel.NONE)
	private String number;

	private Date createdDate;

	private BigDecimal balance;

	@Id
	@GeneratedValue
	@Override
	public Long getId() {
		return id;
	}

	@Column(name = "account_number")
	public String getNumber() {
		return number;
	}

	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

}
