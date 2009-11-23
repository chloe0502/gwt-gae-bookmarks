package com.chrome.extension.client.event;

import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.event.shared.GwtEvent;

/**
 * New bookmark event.
 * 
 * @author Gal Dolber
 */
public class NewBookmarkEvent extends GwtEvent<NewBookmarkHandler> {

    private static Type<NewBookmarkHandler> TYPE;

    public static Type<NewBookmarkHandler> getType() {
	return TYPE != null ? TYPE : (TYPE = new Type<NewBookmarkHandler>());
    }

    private Bookmark bookmark;

    public NewBookmarkEvent(Bookmark bookmark) {
	this.bookmark = bookmark;
    }

    @Override
    public GwtEvent.Type<NewBookmarkHandler> getAssociatedType() {
	return getType();
    }

    @Override
    protected void dispatch(NewBookmarkHandler handler) {
	handler.onNewBookmark(bookmark);
    }
}
