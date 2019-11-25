package com.revo.account

import com.revo.account.persistence.Account
import com.revo.account.validation.AccountValidator
import com.revo.core.validation.ValidationResult
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class AccountResourceSpec extends Specification {

    def accountValidator = Mock(AccountValidator)
    def accountService= Mock(AccountService)


    @Subject
    def accountResource = new AccountResource(accountValidator, accountService)


    def 'should retrieve account by account number'() {
        setup:
        def accountNumber = "ASD25345"

        when:
        AccountDto accountDto = accountResource.account(accountNumber)

        then:
        1 * accountService.findByAccountNumber(accountNumber) >> new Account(number: accountNumber, balance: BigDecimal.TEN)
        accountDto.number == accountNumber
        accountDto.balance == BigDecimal.TEN
    }

    @Unroll
    def 'should create new account'() {
        setup:
        def accountNumber = "ASD25345"
        def balance = BigDecimal.TEN
        def accountDto = new AccountDto(accountNumber, balance)

        when:
        accountResource.createAccount(accountDto)

        then:
        1 * accountValidator.validate(accountDto) >> validationResult
        saved * accountService.createAccount(accountDto)

        where:
        saved | validationResult
        1     | ValidationResult.OK
        0     | ValidationResult.fail("Fail")
    }

}
