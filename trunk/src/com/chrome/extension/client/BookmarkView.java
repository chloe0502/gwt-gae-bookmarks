package com.chrome.extension.client;

import com.gwt.framework.client.View;

/**
 * Bookmark view.
 * 
 * @author Gal Dolber
 */
public interface BookmarkView extends View {

    /**
     * Handlers.
     * 
     * @author Gal Dolber
     */
    interface Handlers {
	boolean canDelete();

	void onClick();

	void onDelete();
    }

    void setHandlers(Handlers handlers);

    void setImageUrl(String icon);

    void setLabel(String name);
}
