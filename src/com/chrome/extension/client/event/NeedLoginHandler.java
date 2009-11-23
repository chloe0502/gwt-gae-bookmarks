package com.chrome.extension.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Need login.
 * 
 * @author Gal Dolber
 */
public interface NeedLoginHandler extends EventHandler {

    void onNeedLogin();
}
