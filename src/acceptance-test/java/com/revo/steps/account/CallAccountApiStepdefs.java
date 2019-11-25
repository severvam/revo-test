package com.revo.steps.account;

import com.revo.AppApi;
import com.revo.account.AccountDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class CallAccountApiStepdefs {

    private String accountId;
    private AccountDto accountDto;

    private AppApi appApi = new AppApi();

    @Before
    public void readProps() throws IOException {
        appApi.init();
    }

    @Given("account Id {string}")
    public void accountId(String accountId) {
        this.accountId = accountId;
    }

    @When("client call account api with id")
    public void clientCallAccountApiWithId() throws IOException {
        accountDto = appApi.getAccount(accountId);
    }

    @Then("account balance {string} and id {string} retrieved")
    public void accountBalanceAndIdASDRetrieved(String balance, String accountId) {
        assertEquals(new BigDecimal(balance).setScale(5, RoundingMode.HALF_UP), accountDto.getBalance());
        assertEquals(accountId, accountDto.getNumber());
    }

    @Given("random account id")
    public void randomAccountId() {
        accountId = UUID.randomUUID().toString();
    }

    @And("client saves new account information with balance {string}")
    public void clientSavesNewAccountInformationWithBalance(String balance) throws IOException {
        AccountDto accountDto = new AccountDto(accountId, new BigDecimal(balance));
        appApi.createAccount(accountDto);
    }

    @Then("account balance {string} retrieved")
    public void accountBalanceRetrieved(String balance) throws IOException {
        accountDto = appApi.getAccount(accountId);

        assertEquals(new BigDecimal(balance).setScale(5, RoundingMode.HALF_UP), accountDto.getBalance());
        assertEquals(accountId, accountDto.getNumber());
    }
}
