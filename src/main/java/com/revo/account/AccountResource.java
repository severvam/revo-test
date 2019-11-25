package com.revo.account;

import com.revo.account.persistence.Account;
import com.revo.account.persistence.AccountDao;
import com.revo.account.validation.AccountValidator;
import com.revo.core.OperationResult;
import com.revo.core.validation.ValidationResult;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

	private final AccountValidator accountValidator;
	private final AccountService accountService;

	@Inject
	public AccountResource(AccountValidator accountValidator, AccountService accountService) {
		this.accountValidator = accountValidator;
		this.accountService = accountService;
	}

	@GET
	@Path("/{accountNumber}")
	public AccountDto account(@PathParam("accountNumber") String number) {
		final Account account = accountService.findByAccountNumber(number);
		return new AccountDto(account.getNumber(), account.getBalance());
	}

	@POST
	public Response createAccount(AccountDto accountDto) {
		final ValidationResult validate = accountValidator.validate(accountDto);
		if (validate.isValid()) {
			accountService.createAccount(accountDto);
		}
		return Response.ok(OperationResult.create(validate.isValid(), validate.getMessage())).build();
	}

}
