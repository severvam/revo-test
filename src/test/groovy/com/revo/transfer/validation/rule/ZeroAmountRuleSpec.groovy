package com.revo.transfer.validation.rule

import com.revo.core.validation.ValidationResult
import com.revo.transfer.TransferDto
import com.revo.transfer.validation.TransferStatus
import spock.lang.Specification

class ZeroAmountRuleSpec extends Specification {

    def zeroAmountRule = new ZeroAmountRule()

    def 'should check is transfer amount zero'() {
        setup:
        def dto = new TransferDto(null, null, amount)

        when:
        ValidationResult validationResult = zeroAmountRule.validate(dto)

        then:
        validationResult == expectedValidationResult

        where:
        amount           || expectedValidationResult
        BigDecimal.ONE   || ValidationResult.OK
        BigDecimal.ZERO  || ValidationResult.fail(TransferStatus.ERROR_ZERO_AMOUNT.toString())
    }

}
