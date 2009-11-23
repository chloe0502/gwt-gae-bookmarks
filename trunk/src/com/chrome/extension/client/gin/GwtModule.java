package com.chrome.extension.client.gin;

import com.chrome.extension.client.AddBookmarkPresenter;
import com.chrome.extension.client.AddBookmarkView;
import com.chrome.extension.client.BookmarkPresenter;
import com.chrome.extension.client.BookmarkView;
import com.chrome.extension.client.MainPresenter;
import com.chrome.extension.client.MainView;
import com.chrome.extension.client.PersistenceService;
import com.chrome.extension.client.ajax.PersistenceServiceImpl;
import com.chrome.extension.client.presenters.AddBookmarkPresenterImpl;
import com.chrome.extension.client.presenters.BookmarkPresenterImpl;
import com.chrome.extension.client.presenters.MainPresenterImpl;
import com.chrome.extension.client.widgets.AddBookmarkViewImpl;
import com.chrome.extension.client.widgets.BookmarkViewImpl;
import com.chrome.extension.client.widgets.MainViewImpl;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.gwt.framework.client.EventBus;
import com.gwt.framework.client.EventBusImpl;

/**
 * Gwt web module.
 */
public class GwtModule extends AbstractGinModule {

    @Override
    protected void configure() {

	// Persistence
	bind(PersistenceService.class).to(PersistenceServiceImpl.class).in(
		Singleton.class);

	// Event bus
	bind(EventBus.class).to(EventBusImpl.class).in(Singleton.class);

	// MVP
	bind(MainPresenter.class).to(MainPresenterImpl.class);
	bind(MainView.class).to(MainViewImpl.class);
	bind(BookmarkView.class).to(BookmarkViewImpl.class);
	bind(BookmarkPresenter.class).to(BookmarkPresenterImpl.class);
	bind(AddBookmarkView.class).to(AddBookmarkViewImpl.class);
	bind(AddBookmarkPresenter.class).to(AddBookmarkPresenterImpl.class);
    }
}
