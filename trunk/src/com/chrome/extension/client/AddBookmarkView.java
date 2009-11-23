package com.chrome.extension.client;

import com.gwt.framework.client.View;

/**
 * Add bookmark view.
 * 
 * @author Gal Dolber
 */
public interface AddBookmarkView extends View {

    /**
     * Handlers.
     * 
     * @author Gal Dolber
     */
    interface Handlers {
	void onAddBookmark(String name, String imageUrl, String url);
    }

    void setHandlers(Handlers handlers);

    void showPopup();
}
