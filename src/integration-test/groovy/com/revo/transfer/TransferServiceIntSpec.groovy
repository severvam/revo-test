package com.revo.transfer

import com.revo.IntegrationSpec
import com.revo.account.persistence.AccountDao

class TransferServiceIntSpec extends IntegrationSpec {

    def 'should pass validation, all ok'() {
        setup:
        TransferService transferService = injector.getInstance(TransferService)
        AccountDao accountDao = injector.getInstance(AccountDao)

        def accountFrom = createAccount(BigDecimal.TEN)
        def accountTo = createAccount(BigDecimal.TEN)

        accountDao.persist(accountFrom)
        accountDao.persist(accountTo)

        def transferDto = new TransferDto(accountFrom.number, accountTo.number, BigDecimal.TEN)

        when:
        transferService.transfer(transferDto)

        then:
        accountDao.findByAccountNumber(accountFrom.number).balance == BigDecimal.ZERO.setScale(5)
        accountDao.findByAccountNumber(accountTo.number).balance == BigDecimal.valueOf(20).setScale(5)
    }

}
