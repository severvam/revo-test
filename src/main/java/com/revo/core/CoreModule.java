package com.revo.core;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.revo.core.web.CoreWebModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        initProperties();
    }

    private void initProperties() {
        try {
            Properties props = new Properties();
            props.load(CoreWebModule.class.getResourceAsStream("/app.properties"));
            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            log.error("Could not load config: ", e);
            System.exit(1);
        }
    }

}
