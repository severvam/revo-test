package com.revo

import com.google.inject.Key
import com.google.inject.name.Names

class AppSpec extends IntegrationSpec {

    def 'should initialize context of the application'() {
        setup:
        String applicationPort = injector.getInstance(Key.get(String.class, Names.named(App.APP_PORT)))

        expect:
        '3000' == applicationPort
    }

}
