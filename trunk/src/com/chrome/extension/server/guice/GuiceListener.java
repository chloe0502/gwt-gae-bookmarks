package com.chrome.extension.server.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * Guice servlet listener.
 * @author Gal Dolber
 */
public class GuiceListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
	return Guice.createInjector(new GuiceModule());
    }
}
