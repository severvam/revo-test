package com.revo

import com.google.inject.Injector
import com.revo.core.persistence.DatabaseConfig
import spock.lang.Specification

class IntegrationSpec extends Specification {

    Injector injector
    H2Server h2Server

    def setup() {
        def app = new App()
        h2Server = new H2Server()
        injector = app.initContext()
        h2Server.run(injector.getInstance(DatabaseConfig))
        injector.injectMembers(this)
    }

}
