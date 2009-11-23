package com.chrome.extension.client;

import com.chrome.extension.client.model.Bookmark;
import com.gwt.framework.client.Presenter;

/**
 * Bookmark presenter.
 * 
 * @author Gal Dolber
 */
public interface BookmarkPresenter extends Presenter<BookmarkView> {

    Bookmark getBookmark();

    void setBookmark(Bookmark bookmark);
}
