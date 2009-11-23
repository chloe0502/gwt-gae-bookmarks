package com.chrome.extension.client.gin;

import com.chrome.extension.client.AddBookmarkPresenter;
import com.chrome.extension.client.BookmarkPresenter;
import com.chrome.extension.client.BookmarkView;
import com.chrome.extension.client.MainPresenter;
import com.chrome.extension.client.PersistenceService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.gwt.framework.client.EventBus;

/**
 * Injector.
 */
@GinModules(GwtModule.class)
public interface GwtInjector extends Ginjector {

    /**
     * Instance.
     */
    GwtInjector INJECTOR = GWT.create(GwtInjector.class);

    AddBookmarkPresenter getAddBookmarkPresenter();

    BookmarkPresenter getBookmarkPresenter();

    BookmarkView getBookmarkView();

    EventBus getEventBus();

    MainPresenter getMainPresenter();

    PersistenceService getPersistenceService();
}
