package com.revo.transfer

import com.revo.core.validation.ValidationResult
import com.revo.transfer.validation.TransferValidator
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class TransferResourceSpec extends Specification {

    def transferValidator = Mock(TransferValidator)
    def transferService = Mock(TransferService)


    @Subject
    def transferResource = new TransferResource(transferValidator, transferService)

    @Unroll
    def 'should transfer data from one account to another'() {
        setup:
        def transferDto = new TransferDto(null, null, null)

        when:
        transferResource.transfer(transferDto)

        then:
        1 * transferValidator.validate(transferDto) >> validationResult
        saved * transferService.transfer(transferDto)

        where:
        saved | validationResult
        1     | ValidationResult.OK
        0     | ValidationResult.fail("Fail")
    }

}
