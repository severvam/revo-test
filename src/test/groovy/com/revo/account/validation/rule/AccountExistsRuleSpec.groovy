package com.revo.account.validation.rule

import com.revo.account.AccountDto
import com.revo.account.persistence.Account
import com.revo.account.persistence.AccountDao
import com.revo.account.validation.AccountStatus
import com.revo.core.validation.ValidationResult
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class AccountExistsRuleSpec extends Specification {

    def accountDao = Stub(AccountDao)

    @Subject
    def accountExistsRule = new AccountExistsRule(accountDao)

    @Unroll
    def 'should validate is the account already exists'() {
        setup:
        def accountNumber = "ABSD003245"
        def parameter = new AccountDto(accountNumber, BigDecimal.ONE)
        accountDao.findByAccountNumber(accountNumber) >> existingAccount

        when:
        ValidationResult result = accountExistsRule.validate(parameter)

        then:
        result == expectedResult

        where:
        existingAccount || expectedResult
        new Account()   || ValidationResult.fail(AccountStatus.ACCOUNT_ALREADY_EXISTS.toString())
        null            || ValidationResult.OK
    }

}
