package com.revo.transfer.validation.rule

import com.revo.core.validation.ValidationResult
import com.revo.transfer.TransferDto
import com.revo.transfer.validation.TransferStatus
import spock.lang.Specification

class NegativeAmountRuleSpec extends Specification {

    def negativeAmountRule = new NegativeAmountRule()

    def 'should check is transfer amount negative'() {
        setup:
        def dto = new TransferDto(null, null, amount)

        when:
        ValidationResult validationResult = negativeAmountRule.validate(dto)

        then:
        validationResult == expectedValidationResult

        where:
        amount                  || expectedValidationResult
        BigDecimal.ONE          || ValidationResult.OK
        BigDecimal.valueOf(-1)  || ValidationResult.fail(TransferStatus.ERROR_NEGATIVE_AMOUNT.toString())
    }

}
