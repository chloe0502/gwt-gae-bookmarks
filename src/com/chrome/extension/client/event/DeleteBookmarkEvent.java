package com.chrome.extension.client.event;

import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Delete event.
 * 
 * @author Gal Dolber
 */
public class DeleteBookmarkEvent extends GwtEvent<DeleteBookmarkHandler> {

    private static Type<DeleteBookmarkHandler> TYPE;

    public static Type<DeleteBookmarkHandler> getType() {
	return TYPE != null ? TYPE : (TYPE = new Type<DeleteBookmarkHandler>());
    }

    private Bookmark bookmark;

    public DeleteBookmarkEvent(Bookmark bookmark) {
	this.bookmark = bookmark;
    }

    @Override
    public GwtEvent.Type<DeleteBookmarkHandler> getAssociatedType() {
	return DeleteBookmarkEvent.getType();
    }

    @Override
    protected void dispatch(DeleteBookmarkHandler handler) {
	handler.onDelete(bookmark);
    }

}
