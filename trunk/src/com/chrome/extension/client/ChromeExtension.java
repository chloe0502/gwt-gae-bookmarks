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
public class ChromeExtension implements EntryPoint {

    private EventBus eventBus;

    @Override
    public void onModuleLoad() {
	RootLayoutPanel.get().add((Widget) GwtInjector.INJECTOR
		.getMainPresenter().getView());

	eventBus = GwtInjector.INJECTOR.getEventBus();

	// Login handler
	eventBus.addHandler(new NeedLoginHandler() {
	    @Override
	    public void onNeedLogin() {
		GwtInjector.INJECTOR.getPersistenceService().checkLogged("http://mismarks.appspot.com/", new AsyncCallback<String>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Error login in");
		    }

		    @Override
		    public void onSuccess(String result) {
			createChromeTab(result);
		    }
		});
	    }
	}, NeedLoginEvent.getType());

	// First reload
	eventBus.fireEvent(new ReloadEvent());
    }

    private native void createChromeTab(String result) /*-{
	chrome.tabs.create(
		{
		    url: result,
		    selected: true
		}, 
		function(tab) {
		}
	);
    }-*/;
}