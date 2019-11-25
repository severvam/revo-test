package com.revo.steps.transfer;

import com.revo.AppApi;
import com.revo.account.AccountDto;
import com.revo.core.OperationResult;
import com.revo.transfer.TransferDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class TransferFundsStepdefs {

    private String accountIdFrom;
    private String accountIdTo;
    private String message;

    private AppApi appApi = new AppApi();

    @Before
    public void readProps() throws IOException {
        appApi.init();
    }

    @Given("two accounts with balance {string} and {string}")
    public void twoAccountsWithBalanceAnd(String balanceFrom, String balanceTo) throws IOException {
        accountIdFrom = UUID.randomUUID().toString();
        accountIdTo = UUID.randomUUID().toString();
        AccountDto accountDtoFrom = new AccountDto(accountIdFrom, new BigDecimal(balanceFrom));
        AccountDto accountDtoTo = new AccountDto(accountIdTo, new BigDecimal(balanceTo));

        appApi.createAccount(accountDtoFrom);
        appApi.createAccount(accountDtoTo);
    }

    @When("client transfers from first to second {string}")
    public void clientTransfersFromFirstToSecond(String amount) throws IOException {
        TransferDto transferDto = new TransferDto(accountIdFrom, accountIdTo, new BigDecimal(amount));
        OperationResult operationResult = appApi.transfer(transferDto);
        message = operationResult.getMessage();
    }

    @Then("first account contains {string} and second {string}")
    public void firstAccountContainsAndSecond(String balanceFrom, String balanceTo) throws IOException {
        AccountDto accountFrom = appApi.getAccount(accountIdFrom);
        AccountDto accountTo = appApi.getAccount(accountIdTo);

        assertEquals(new BigDecimal(balanceFrom).setScale(5, RoundingMode.HALF_UP), accountFrom.getBalance());
        assertEquals(new BigDecimal(balanceTo).setScale(5, RoundingMode.HALF_UP), accountTo.getBalance());
    }


    @Then("receives error message {string}")
    public void receivesErrorMessage(String message) {
        assertEquals(this.message, message);
    }
}
