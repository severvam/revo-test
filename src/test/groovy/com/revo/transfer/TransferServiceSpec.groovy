package com.revo.transfer

import com.dieselpoint.norm.Transaction
import com.revo.account.persistence.Account
import com.revo.account.persistence.AccountDao
import com.revo.core.AppDateTimeService
import com.revo.transfer.persistence.Transfer
import com.revo.transfer.persistence.TransferDao
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static java.math.BigDecimal.valueOf

class TransferServiceSpec extends Specification {

    def transferDao = Mock(TransferDao)
    def accountDao = Mock(AccountDao)
    def appDateTimeService = Stub(AppDateTimeService)

    def transaction = Stub(Transaction)

    @Subject
    def transferService = new TransferService(transferDao, accountDao, appDateTimeService)

    @Unroll
    def 'should transfer funds from one account to another'() {
        setup:
        def transferDto = new TransferDto(accountNumberFrom, accountNumberTo, amount)
        def accountFrom = new Account(balance: balanceFrom)
        def accountTo = new Account(balance: balanceTo)

        when:
        transferService.transfer(transferDto)


        then:
        1 * accountDao.findByAccountNumber(accountNumberFrom) >> accountFrom
        1 * accountDao.findByAccountNumber(accountNumberTo) >> accountTo
        1 * transferDao.startTransaction() >> transaction
        1 * transferDao.persist(_ as Transfer, transaction)
        1 * accountDao.update(accountFrom, transaction)
        1 * accountDao.update(accountTo, transaction)

        accountFrom.balance == updatedBalanceFrom
        accountTo.balance == updatedBalanceTo

        where:
        accountNumberFrom | accountNumberTo | balanceFrom  | balanceTo   | amount      || updatedBalanceFrom || updatedBalanceTo
        'SDFSD345345'     | 'FGHF456456'    | valueOf(50)  | valueOf(10) | valueOf(20) || valueOf(30)        || valueOf(30)
        'SDFSD345345'     | 'FGHF456456'    | valueOf(20)  | valueOf(0)  | valueOf(20) || valueOf(0)         || valueOf(20)
    }
}
