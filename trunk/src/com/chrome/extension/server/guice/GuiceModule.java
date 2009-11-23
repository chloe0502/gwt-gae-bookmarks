package com.chrome.extension.server.guice;

import com.chrome.extension.client.ajax.AppService;
import com.chrome.extension.server.AppServiceImpl;
import com.google.inject.servlet.ServletModule;

/**
 * Guice servlet module.
 * @author Gal Dolber
 */
public class GuiceModule extends ServletModule {

    @Override
    protected void configureServlets() {
	serve("/mismarks/GWT.rpc").with(GuiceRemoteServiceServlet.class);
	
	bind(AppService.class).to(AppServiceImpl.class);
    }
}
