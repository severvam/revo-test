package com.revo

import com.google.inject.Injector
import com.revo.account.persistence.Account
import com.revo.core.persistence.DatabaseConfig
import spock.lang.Shared
import spock.lang.Specification

class IntegrationSpec extends Specification {

    @Shared
    Injector injector
    @Shared
    H2Server h2Server

    def setupSpec() {
        def app = new App()
        h2Server = new H2Server()
        injector = app.initContext()
        h2Server.run(injector.getInstance(DatabaseConfig))
    }

    def cleanupSpec() {
        h2Server.stop()
    }

    Account createAccount(BigDecimal balance) {
        def account = new Account()
        account.number = UUID.randomUUID().toString()
        account.balance = balance
        account.createdDate = new Date()
        account
    }

}
