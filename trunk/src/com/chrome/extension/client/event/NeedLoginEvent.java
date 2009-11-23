package com.chrome.extension.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Need login event.
 * 
 * @author Gal Dolber
 */
public class NeedLoginEvent extends GwtEvent<NeedLoginHandler> {

    private static Type<NeedLoginHandler> TYPE;

    public static Type<NeedLoginHandler> getType() {
	return TYPE != null ? TYPE : (TYPE = new Type<NeedLoginHandler>());
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<NeedLoginHandler> getAssociatedType() {
	return getType();
    }

    @Override
    protected void dispatch(NeedLoginHandler handler) {
	handler.onNeedLogin();
    }
}
