package com.revo


import javax.inject.Inject
import javax.inject.Named

class AppSpec extends IntegrationSpec {

    @Inject
    @Named(App.APP_PORT)
    String applicationPort

    def 'should initialize context of the application'() {
        expect:
        '3000' == applicationPort
    }

}
