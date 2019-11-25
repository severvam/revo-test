package com.revo.account.validation

import com.revo.IntegrationSpec
import com.revo.account.AccountDto
import com.revo.account.persistence.AccountDao
import com.revo.core.validation.ValidationResult

class AccountValidatorSpec extends IntegrationSpec {

    def 'should pass validation, all ok'() {
        setup:
        AccountValidator accountValidator = injector.getInstance(AccountValidator)
        def accountDto = new AccountDto('any-random-account', BigDecimal.valueOf(20))

        when:
        ValidationResult validationResult = accountValidator.validate(accountDto)

        then:
        validationResult == ValidationResult.OK
    }

    def 'should pass validation, empty balance possible'() {
        setup:
        AccountValidator accountValidator = injector.getInstance(AccountValidator)
        def accountDto = new AccountDto('any-random-account-2', BigDecimal.ZERO)

        when:
        ValidationResult validationResult = accountValidator.validate(accountDto)

        then:
        validationResult == ValidationResult.OK
    }

    def 'should fail validation, negative balance'() {
        setup:
        AccountValidator accountValidator = injector.getInstance(AccountValidator)
        def accountDto = new AccountDto('any-random-account-3', BigDecimal.valueOf(-20))

        when:
        ValidationResult validationResult = accountValidator.validate(accountDto)

        then:
        validationResult == ValidationResult.fail(AccountStatus.NEGATIVE_INITIAL_AMOUNT.toString())
    }

    def 'should fail validation, account exists'() {
        setup:
        AccountValidator accountValidator = injector.getInstance(AccountValidator)
        AccountDao accountDao = injector.getInstance(AccountDao)

        def account = createAccount(BigDecimal.valueOf(20))
        accountDao.persist(account)

        def accountDto = new AccountDto(account.number, BigDecimal.valueOf(20))

        when:
        ValidationResult validationResult = accountValidator.validate(accountDto)

        then:
        validationResult == ValidationResult.fail(AccountStatus.ACCOUNT_ALREADY_EXISTS.toString())
    }

}
