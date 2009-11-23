package com.chrome.extension.client;

import java.util.List;

import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Persistence service.
 * 
 * @author Gal Dolber
 */
public interface PersistenceService {

    void addBookmark(Bookmark bookmark, AsyncCallback<Long> callback);

    void checkLogged(String url, AsyncCallback<String> callback);

    void delete(Long id, AsyncCallback<Void> callback);

    void getBookmarks(AsyncCallback<List<Bookmark>> callback);
}
