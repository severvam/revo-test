package com.revo.account.persistence

import com.revo.IntegrationSpec

import java.sql.Timestamp

class AccountDaoSpec extends IntegrationSpec {

    def 'should store data in db'() {
        setup:
        AccountDao accountDao = injector.getInstance(AccountDao)

        def account = new Account()
        account.number = "1"
        account.balance = BigDecimal.TEN
        account.createdDate = new Date()

        when:
        accountDao.persist(account)

        then:
        account.id != null
    }


    def 'should find account by account number'() {
        setup:
        AccountDao accountDao = injector.getInstance(AccountDao)

        def account = new Account()
        account.number = "ACV14245"
        account.balance = BigDecimal.TEN.setScale(5)
        account.createdDate = new Timestamp(new Date().getTime())
        accountDao.persist(account)

        when:
        def foundAccount = accountDao.findByAccountNumber(account.number)

        then:
        foundAccount == account
    }

}
