package com.chrome.extension.client.ajax;

import java.util.List;

import com.chrome.extension.client.model.Bookmark;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AppServiceAsync {
    void addBookmark(Bookmark bookmark, AsyncCallback<Long> callback);

    void deleteBookmark(Long id, AsyncCallback<Void> callback);

    void getBookmarks(AsyncCallback<List<Bookmark>> callback);

    void isLogged(String location, AsyncCallback<String> callback);
}
