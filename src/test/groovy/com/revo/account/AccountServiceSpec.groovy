package com.revo.account

import com.revo.account.persistence.Account
import com.revo.account.persistence.AccountDao
import com.revo.core.AppDateTimeService
import spock.lang.Specification
import spock.lang.Subject

class AccountServiceSpec extends Specification {

    def accountDao = Mock(AccountDao)
    def appDateTimeService = Mock(AppDateTimeService)

    @Subject
    def accountService = new AccountService(accountDao, appDateTimeService)

    def 'should save new account'() {
        setup:
        def accountDto = new AccountDto("ASD234", BigDecimal.TEN)
        def date = new Date()

        when:
        accountService.createAccount(accountDto)

        then:
        1 * appDateTimeService.currentDate() >> date
        1 * accountDao.persist(_ as Account) >> {args ->
            Account account = args[0]
            assert account.number == accountDto.number
            assert account.balance == accountDto.balance
            assert account.createdDate == date
        }
    }

    def 'should return account by account number'() {
        setup:
        def accountNumber = 'EFGFD345345'

        when:
        Account account = accountService.findByAccountNumber(accountNumber)

        then:
        1 * accountDao.findByAccountNumber(accountNumber) >> new Account(number: accountNumber)
        account.number == accountNumber
    }

}
