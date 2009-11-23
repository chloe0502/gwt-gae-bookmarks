package com.chrome.extension.client.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Reload handler.
 * 
 * @author Gal Dolber
 */
public interface ReloadHandler extends EventHandler {

    void onReload();
}
