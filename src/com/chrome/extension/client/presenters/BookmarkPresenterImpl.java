package com.chrome.extension.client.presenters;

import com.chrome.extension.client.BookmarkPresenter;
import com.chrome.extension.client.BookmarkView;
import com.chrome.extension.client.BookmarkView.Handlers;
import com.chrome.extension.client.event.DeleteBookmarkEvent;
import com.chrome.extension.client.gin.GwtInjector;
import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwt.framework.client.EventBus;

/**
 * Bookmark view implementation.
 * 
 * @author Gal Dolber
 */
public class BookmarkPresenterImpl implements BookmarkPresenter, Handlers {

    private BookmarkView view;

    private Bookmark bookmark;

    private EventBus eventBus;

    @Inject
    public BookmarkPresenterImpl(EventBus eventBus, BookmarkView view) {
	this.view = view;
	this.eventBus = eventBus;
	view.setHandlers(this);
    }

    @Override
    public boolean canDelete() {
	if (bookmark.getId() != null) {
	    return true;
	}
	return false;
    }

    @Override
    public Bookmark getBookmark() {
	return bookmark;
    }

    @Override
    public BookmarkView getView() {
	return view;
    }

    @Override
    public void onClick() {
	Window.open(bookmark.getUrl(), bookmark.getName(), "");
    }

    @Override
    public void onDelete() {
	GwtInjector.INJECTOR.getPersistenceService().delete(bookmark.getId(),
		new AsyncCallback<Void>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Error deleting");
		    }

		    @Override
		    public void onSuccess(Void result) {
			eventBus.fireEvent(new DeleteBookmarkEvent(bookmark));
		    }
		});
    }

    @Override
    public void setBookmark(Bookmark bookmark) {
	this.bookmark = bookmark;
	view.setLabel(bookmark.getName());
	view.setImageUrl(bookmark.getIcon());
    }
}
