package com.chrome.extension.client.ajax;

import java.util.List;

import com.chrome.extension.client.PersistenceService;
import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Persistence service on app engine.
 * 
 * @author Gal Dolber
 */
public class PersistenceServiceImpl implements PersistenceService {

    private static AppServiceAsync service = GWT.create(AppService.class);

    @Override
    public void addBookmark(Bookmark bookmark, AsyncCallback<Long> callback) {
	service.addBookmark(bookmark, callback);
    }

    @Override
    public void checkLogged(String url, AsyncCallback<String> callback) {
	service.isLogged(url, callback);
    }

    @Override
    public void delete(Long id, AsyncCallback<Void> callback) {
	service.deleteBookmark(id, callback);
    }

    @Override
    public void getBookmarks(AsyncCallback<List<Bookmark>> callback) {
	service.getBookmarks(callback);
    }
}
