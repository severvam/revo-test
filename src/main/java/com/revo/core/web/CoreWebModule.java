package com.revo.core.web;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class CoreWebModule extends AbstractModule {

	@Override
	protected void configure() {
		super.configure();
		bind(GsonMessageBodyHandler.class).in(Singleton.class);
		bind(DefaultExceptionHandler.class).in(Singleton.class);
	}

}