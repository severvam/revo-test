package com.revo.core;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.revo.core.web.CoreWebModule;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.api.validation.ResteasyViolationExceptionMapper;
import org.jboss.resteasy.plugins.validation.ValidatorContextResolver;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        initProperties();
        bind(AppDateTimeService.class).in(Singleton.class);
        bind(ValidationExceptionMapper.class).in(Singleton.class);
        bind(ResteasyViolationExceptionMapper.class).in(Singleton.class);
        bind(ValidatorContextResolver.class).in(Singleton.class);
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
