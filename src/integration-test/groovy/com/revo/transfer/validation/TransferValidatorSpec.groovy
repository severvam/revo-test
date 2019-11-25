package com.revo.transfer.validation

import com.revo.IntegrationSpec
import com.revo.account.persistence.AccountDao
import com.revo.core.validation.ValidationResult
import com.revo.transfer.TransferDto

class TransferValidatorSpec extends IntegrationSpec {

    def 'should pass validation, all ok'() {
        setup:
        TransferValidator transferValidator = injector.getInstance(TransferValidator)
        AccountDao accountDao = injector.getInstance(AccountDao)

        def accountFrom = createAccount(BigDecimal.valueOf(20))
        def accountTo = createAccount(BigDecimal.ZERO)

        accountDao.persist(accountFrom)
        accountDao.persist(accountTo)

        def transferDto = new TransferDto(accountFrom.number, accountTo.number, BigDecimal.TEN)

        when:
        ValidationResult validationResult = transferValidator.validate(transferDto)

        then:
        validationResult == ValidationResult.OK
    }

    def 'should fail validation, not enough money'() {
        setup:
        TransferValidator transferValidator = injector.getInstance(TransferValidator)
        AccountDao accountDao = injector.getInstance(AccountDao)

        def accountFrom = createAccount(BigDecimal.TEN)
        def accountTo = createAccount(BigDecimal.TEN)

        accountDao.persist(accountFrom)
        accountDao.persist(accountTo)

        def transferDto = new TransferDto(accountFrom.number, accountTo.number, BigDecimal.valueOf(50))

        when:
        ValidationResult validationResult = transferValidator.validate(transferDto)

        then:
        validationResult == ValidationResult.fail(TransferStatus.ERROR_INSUFFICIENT_FUNDS.toString())
    }

    def 'should fail validation, not existing account'() {
        setup:
        TransferValidator transferValidator = injector.getInstance(TransferValidator)
        AccountDao accountDao = injector.getInstance(AccountDao)

        def accountTo = createAccount(BigDecimal.TEN)

        accountDao.persist(accountTo)

        def transferDto = new TransferDto('Unknown account', accountTo.number, BigDecimal.valueOf(50))

        when:
        ValidationResult validationResult = transferValidator.validate(transferDto)

        then:
        validationResult == ValidationResult.fail(TransferStatus.ERROR_NOT_EXISTING_ACCOUNT.toString())
    }

    def 'should fail validation, negative amount'() {
        setup:
        TransferValidator transferValidator = injector.getInstance(TransferValidator)
        AccountDao accountDao = injector.getInstance(AccountDao)

        def accountFrom = createAccount(BigDecimal.TEN)
        def accountTo = createAccount(BigDecimal.TEN)

        accountDao.persist(accountFrom)
        accountDao.persist(accountTo)

        def transferDto = new TransferDto(accountFrom.number, accountTo.number, BigDecimal.valueOf(-50))

        when:
        ValidationResult validationResult = transferValidator.validate(transferDto)

        then:
        validationResult == ValidationResult.fail(TransferStatus.ERROR_NEGATIVE_AMOUNT.toString())
    }

    def 'should fail validation, zero amount'() {
        setup:
        TransferValidator transferValidator = injector.getInstance(TransferValidator)
        AccountDao accountDao = injector.getInstance(AccountDao)

        def accountFrom = createAccount(BigDecimal.TEN)
        def accountTo = createAccount(BigDecimal.TEN)

        accountDao.persist(accountFrom)
        accountDao.persist(accountTo)

        def transferDto = new TransferDto(accountFrom.number, accountTo.number, BigDecimal.ZERO)

        when:
        ValidationResult validationResult = transferValidator.validate(transferDto)

        then:
        validationResult == ValidationResult.fail(TransferStatus.ERROR_ZERO_AMOUNT.toString())
    }


}
