package com.revo.transfer.validation.rule

import com.revo.account.persistence.Account
import com.revo.account.persistence.AccountDao
import com.revo.core.validation.ValidationResult
import com.revo.transfer.TransferDto
import com.revo.transfer.validation.TransferStatus
import spock.lang.Specification
import spock.lang.Subject

class EnoughFundingValidationRuleSpec extends Specification {

    def accountDao = Mock(AccountDao)

    @Subject
    def enoughFundingValidationRule = new EnoughFundingValidationRule(accountDao)

    def 'should validate that accounts has enough funds to transfer money'() {
        setup:
        def accountNumber = 'SDF435345'
        def dto = new TransferDto(accountNumber, null, amount)

        when:
        ValidationResult validationResult = enoughFundingValidationRule.validate(dto)

        then:
        1 * accountDao.findByAccountNumber(accountNumber) >> new Account(balance: balance)
        validationResult == expectedValidationResult

        where:
        amount           | balance        || expectedValidationResult
        BigDecimal.TEN   | BigDecimal.ONE || ValidationResult.fail(TransferStatus.ERROR_INSUFFICIENT_FUNDS.toString())
        BigDecimal.TEN   | BigDecimal.TEN || ValidationResult.OK
        BigDecimal.ONE   | BigDecimal.TEN || ValidationResult.OK
    }

}
