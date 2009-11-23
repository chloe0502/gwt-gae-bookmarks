package com.chrome.extension.client.presenters;

import java.util.ArrayList;
import java.util.List;

import com.chrome.extension.client.BookmarkPresenter;
import com.chrome.extension.client.MainPresenter;
import com.chrome.extension.client.MainView;
import com.chrome.extension.client.PersistenceService;
import com.chrome.extension.client.event.DeleteBookmarkEvent;
import com.chrome.extension.client.event.DeleteBookmarkHandler;
import com.chrome.extension.client.event.NeedLoginEvent;
import com.chrome.extension.client.event.NewBookmarkEvent;
import com.chrome.extension.client.event.NewBookmarkHandler;
import com.chrome.extension.client.event.ReloadEvent;
import com.chrome.extension.client.event.ReloadHandler;
import com.chrome.extension.client.gin.GwtInjector;
import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwt.framework.client.EventBus;

/**
 * Main presenter.
 * 
 * @author Gal Dolber
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    private ArrayList<Bookmark> bookmarks = new ArrayList<Bookmark>();

    @Inject
    public MainPresenterImpl(final EventBus eventBus, final MainView view,
	    final PersistenceService persistenceService) {
	this.view = view;

	eventBus.addHandler(new ReloadHandler() {
	    @Override
	    public void onReload() {
		persistenceService
			.getBookmarks(new AsyncCallback<List<Bookmark>>() {
			    @Override
			    public void onFailure(Throwable caught) {
				eventBus.fireEvent(new NeedLoginEvent());
			    }

			    @Override
			    public void onSuccess(List<Bookmark> result) {
				for (Bookmark bookmark : result) {
				    addBookmark(bookmark);
				}
			    }
			});
	    }
	}, ReloadEvent.getType());

	eventBus.addHandler(new NewBookmarkHandler() {
	    @Override
	    public void onNewBookmark(Bookmark bookmark) {
		addBookmark(bookmark);
	    }
	}, NewBookmarkEvent.getType());

	eventBus.addHandler(new DeleteBookmarkHandler() {
	    @Override
	    public void onDelete(Bookmark bookmark) {
		view.reset();
		bookmarks.remove(bookmark);
		for (Bookmark b : bookmarks) {
		    BookmarkPresenter bookmarkPresenter = GwtInjector.INJECTOR
			    .getBookmarkPresenter();
		    bookmarkPresenter.setBookmark(b);
		    view.add(bookmarkPresenter.getView());
		    view.add(bookmarkPresenter.getView());
		}
	    }
	}, DeleteBookmarkEvent.getType());
    }

    @Override
    public void addBookmark(Bookmark bookmark) {
	bookmarks.add(bookmark);
	BookmarkPresenter bookmarkPresenter = GwtInjector.INJECTOR
		.getBookmarkPresenter();
	bookmarkPresenter.setBookmark(bookmark);
	view.add(bookmarkPresenter.getView());
    }

    @Override
    public MainView getView() {
	return view;
    }
}
