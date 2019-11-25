package com.revo.account.validation.rule

import com.revo.account.AccountDto
import com.revo.account.validation.AccountStatus
import com.revo.core.validation.ValidationResult
import spock.lang.Specification
import spock.lang.Unroll

class NegativeInitialBalanceRuleSpec extends Specification {

    def accountExistsRule = new NegativeInitialBalanceRule()

    @Unroll
    def 'should validate is the balance positive or zero'() {
        setup:
        def accountNumber = "ABSD003245"
        def parameter = new AccountDto(accountNumber, balance)

        when:
        ValidationResult result = accountExistsRule.validate(parameter)

        then:
        result == expectedResult

        where:
        balance                 || expectedResult
        BigDecimal.ONE          ||  ValidationResult.OK
        BigDecimal.ZERO         || ValidationResult.OK
        BigDecimal.valueOf(-1)  || ValidationResult.fail(AccountStatus.NEGATIVE_INITIAL_AMOUNT.toString())
    }

}
