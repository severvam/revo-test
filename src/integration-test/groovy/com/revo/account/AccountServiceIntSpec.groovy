package com.revo.account

import com.revo.IntegrationSpec
import com.revo.account.persistence.Account
import com.revo.account.persistence.AccountDao

class AccountServiceIntSpec extends IntegrationSpec {

    def 'should pass validation, all ok'() {
        setup:
        AccountDao accountDao = injector.getInstance(AccountDao)
        AccountService accountService = injector.getInstance(AccountService)
        def accountNumber = 'any-new-random-account'
        def accountDto = new AccountDto(accountNumber, BigDecimal.valueOf(20))

        when:
        accountService.createAccount(accountDto)

        then:
        Account account = accountDao.findByAccountNumber(accountNumber)
        account != null
        account.id != null
    }

}
