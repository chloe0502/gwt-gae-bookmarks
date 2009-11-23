package com.chrome.extension.client.presenters;

import com.chrome.extension.client.AddBookmarkPresenter;
import com.chrome.extension.client.AddBookmarkView;
import com.chrome.extension.client.AddBookmarkView.Handlers;
import com.chrome.extension.client.event.NewBookmarkEvent;
import com.chrome.extension.client.gin.GwtInjector;
import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwt.framework.client.EventBus;

/**
 * Add presenter implementation.
 * 
 * @author Gal Dolber
 */
public class AddBookmarkPresenterImpl implements AddBookmarkPresenter, Handlers {

    private AddBookmarkView view;

    private EventBus eventBus;

    @Inject
    public AddBookmarkPresenterImpl(EventBus eventBus, AddBookmarkView view) {
	this.eventBus = eventBus;
	this.view = view;
	view.setHandlers(this);
    }

    @Override
    public AddBookmarkView getView() {
	return view;
    }

    @Override
    public void onAddBookmark(String name, String imageUrl, String url) {
	final Bookmark bookmark = new Bookmark(url, imageUrl, name);
	eventBus.fireEvent(new NewBookmarkEvent(bookmark));
	GwtInjector.INJECTOR.getPersistenceService().addBookmark(bookmark,
		new AsyncCallback<Long>() {
		    @Override
		    public void onFailure(Throwable caught) {
			Window.alert("Error adding bookmark");
		    }

		    @Override
		    public void onSuccess(Long id) {
			bookmark.setId(id);
		    }
		});
    }
}
