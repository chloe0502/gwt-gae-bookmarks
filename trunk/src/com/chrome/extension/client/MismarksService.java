package com.chrome.extension.client;

import com.chrome.extension.client.event.NeedLoginEvent;
import com.chrome.extension.client.event.NeedLoginHandler;
import com.chrome.extension.client.event.ReloadEvent;
import com.chrome.extension.client.gin.GwtInjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwt.framework.client.EventBus;

/**
 * Chrome bookmark service.
 * 
 * @author Gal Dolber
 */
public class MismarksService implements EntryPoint {

    @Override
    public void onModuleLoad() {
	MainPresenter mainPresenter = GwtInjector.INJECTOR
	.getMainPresenter();
	RootLayoutPanel.get().add((Widget) mainPresenter.getView());

	EventBus eventBus = GwtInjector.INJECTOR.getEventBus();

	// Login handler
	eventBus.addHandler(new NeedLoginHandler() {
	    @Override
	    public void onNeedLogin() {
		GwtInjector.INJECTOR.getPersistenceService().checkLogged(Window.Location.getHref(), new AsyncCallback<String>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Error login in");
		    }

		    @Override
		    public void onSuccess(String result) {
			Window.Location.replace(result);
		    }
		});
	    }
	}, NeedLoginEvent.getType());

	// First reload
	eventBus.fireEvent(new ReloadEvent());
    }
}