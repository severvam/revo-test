package com.revo.transfer.persistence

import com.revo.IntegrationSpec
import com.revo.account.persistence.Account
import com.revo.account.persistence.AccountDao

import javax.inject.Inject

class TransferDaoSpec extends IntegrationSpec {

    @Inject
    TransferDao transferDao
    @Inject
    AccountDao accountDao

    def 'should store data in db'() {
        setup:
        def accountFrom = new Account(number: "1-from", createdDate: new Date(), balance: 100)
        def accountTo = new Account(number: "1-to", createdDate: new Date(), balance: 10)
        accountDao.persist(accountFrom)
        accountDao.persist(accountTo)

        def transfer = new Transfer()
        transfer.accountFromId = accountFrom.id
        transfer.accountToId = accountTo.id
        transfer.amount = 50
        transfer.transactionDate = new Date()

        when:
        transferDao.persist(transfer)

        then:
        transfer.id != null
    }
}