package com.revo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.revo.core.persistence.DatabaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;

@Slf4j
public class App {

    private static final String APP_PORT = "application.port";

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        Log.setLog(new Slf4jLog(App.class.getName()));
        final Injector injector = initContext();
        new H2Server().run(injector.getInstance(DatabaseConfig.class));

        final String port = injector.getInstance(Key.get(String.class, Names.named(APP_PORT)));
        new JettyServer().run(Integer.parseInt(port), injector.getInstance(GuiceResteasyBootstrapServletContextListener.class));
    }

    Injector initContext() {
        final Injector injector = Guice.createInjector(new Modules().modules());
        injector.getAllBindings();
        injector.createChildInjector().getAllBindings();
        return injector;
    }

}
