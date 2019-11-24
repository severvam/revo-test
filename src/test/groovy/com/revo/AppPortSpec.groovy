package com.revo

import spock.lang.Specification

class AppPortSpec extends Specification {

    def "application has property to get port from context"() {
        setup:
        def app = new App()

        when:
        def port = app.APP_PORT

        then:
        'application.port' == port
    }

}
