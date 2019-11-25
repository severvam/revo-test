package com.revo.transfer.validation.rule

import com.revo.account.persistence.Account
import com.revo.account.persistence.AccountDao
import com.revo.core.validation.ValidationResult
import com.revo.transfer.TransferDto
import com.revo.transfer.validation.TransferStatus
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class AccountExistsTransferValidationRuleSpec extends Specification {

    def accountDao = Mock(AccountDao)

    @Subject
    def accountExistsTransferValidationRule = new AccountExistsTransferValidationRule(accountDao)


    @Unroll
    def 'should validate accounts for money transfer'() {
        setup:
        def dto = new TransferDto(accountNumberFrom, accountNumberTo, amount)

        when:
        ValidationResult validationResult = accountExistsTransferValidationRule.validate(dto)

        then:
        1 * accountDao.findByAccountNumber(accountNumberFrom) >> accountFrom
        1 * accountDao.findByAccountNumber(accountNumberTo) >> accountTo
        validationResult == expectedValidationResult

        where:
        accountNumberFrom | accountNumberTo | amount          | accountFrom    | accountTo      || expectedValidationResult
        'ASD2343'         | 'DFG435'        | BigDecimal.TEN  | new Account()  | new Account()  || ValidationResult.OK
        'ASD2343'         | 'DFG435'        | BigDecimal.TEN  | null           | new Account()  || ValidationResult.fail(TransferStatus.ERROR_NOT_EXISTING_ACCOUNT.toString())
        'ASD2343'         | 'DFG435'        | BigDecimal.TEN  | new Account()  | null           || ValidationResult.fail(TransferStatus.ERROR_NOT_EXISTING_ACCOUNT.toString())
    }

}
