package com.chrome.extension.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Reload event.
 * 
 * @author Gal Dolber
 */
public class ReloadEvent extends GwtEvent<ReloadHandler> {

    private static Type<ReloadHandler> TYPE;

    public static Type<ReloadHandler> getType() {
	return TYPE != null ? TYPE : (TYPE = new Type<ReloadHandler>());
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ReloadHandler> getAssociatedType() {
	return getType();
    }

    @Override
    protected void dispatch(ReloadHandler handler) {
	handler.onReload();
    }
}
