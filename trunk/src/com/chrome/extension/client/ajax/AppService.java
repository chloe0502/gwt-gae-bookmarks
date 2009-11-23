package com.chrome.extension.client.ajax;

import java.util.List;

import com.chrome.extension.client.model.Bookmark;
import com.chrome.extension.client.model.NotLoggedInException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.rebind.rpc.RemoteServiceBasePath;

/**
 * The client side stub for the RPC service.
 * 
 * The base path is only valid for the extension.
 */
@RemoteServiceBasePath("http://mismarks.appspot.com/mismarks/")
@RemoteServiceRelativePath("GWT.rpc")
public interface AppService extends RemoteService {
    
    Long addBookmark(Bookmark bookmark) throws NotLoggedInException;

    void deleteBookmark(Long id) throws NotLoggedInException;

    List<Bookmark> getBookmarks() throws NotLoggedInException;

    String isLogged(String location);
}
