package com.revo.account.persistence

import com.revo.IntegrationSpec

import javax.inject.Inject

class AccountDaoSpec extends IntegrationSpec {

    @Inject
    AccountDao accountDao

    def 'should store data in db'() {
        setup:
        def account = new Account()
        account.number = "1"
        account.balance = BigDecimal.valueOf(10)
        account.createdDate = new Date()

        when:
        accountDao.persist(account)

        then:
        account.id != null
    }

}
