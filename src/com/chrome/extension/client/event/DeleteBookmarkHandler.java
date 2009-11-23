package com.chrome.extension.client.event;

import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.event.shared.EventHandler;

/**
 * Delete handler.
 * 
 * @author Gal Dolber
 */
public interface DeleteBookmarkHandler extends EventHandler {

    void onDelete(Bookmark bookmark);
}
