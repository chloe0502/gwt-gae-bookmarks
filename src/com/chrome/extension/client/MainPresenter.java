package com.chrome.extension.client;

import com.chrome.extension.client.model.Bookmark;
import com.gwt.framework.client.Presenter;

/**
 * Presenter.
 * 
 * @author Gal Dolber
 */
public interface MainPresenter extends Presenter<MainView> {

    void addBookmark(Bookmark bookmark);
}