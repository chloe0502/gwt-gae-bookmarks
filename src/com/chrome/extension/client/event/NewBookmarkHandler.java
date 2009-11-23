package com.chrome.extension.client.event;

import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.event.shared.EventHandler;

/**
 * New bookmark handler.
 * 
 * @author Gal Dolber
 */
public interface NewBookmarkHandler extends EventHandler {

    void onNewBookmark(Bookmark bookmark);
}
