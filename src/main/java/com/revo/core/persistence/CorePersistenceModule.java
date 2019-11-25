package com.revo.core.persistence;

import com.google.inject.AbstractModule;

import javax.inject.Singleton;

public class CorePersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        bind(DatabaseConfig.class).in(Singleton.class);
    }

}