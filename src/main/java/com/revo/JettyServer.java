package com.revo;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;

@Slf4j
class JettyServer {

	void run(int port, GuiceResteasyBootstrapServletContextListener listener) {
		final var server = new Server(port);
		final var servletContextHandler = new ServletContextHandler(NO_SESSIONS);
		servletContextHandler.addEventListener(listener);

		servletContextHandler.setContextPath("/api/");
		final var servletHolder = new ServletHolder(HttpServletDispatcher.class);
		servletContextHandler.addServlet(servletHolder, "/*");

		server.setHandler(servletContextHandler);
		try {
			server.start();
			server.join();
		} catch (Exception ex) {
			log.error("Error occurred while starting Jetty", ex);
			System.exit(1);
		} finally {
			server.destroy();
		}
	}

}
