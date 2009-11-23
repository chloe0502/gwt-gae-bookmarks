package com.chrome.extension.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.chrome.extension.client.ajax.AppService;
import com.chrome.extension.client.model.Bookmark;
import com.chrome.extension.client.model.NotLoggedInException;
import com.google.appengine.api.users.User;

/**
 * The server side implementation of the RPC service.
 */
public class AppServiceImpl implements AppService {

    @Override
    public Long addBookmark(Bookmark bookmark) throws NotLoggedInException {
	User user = UserFactory.get().getCurrentUser();
	if (user != null) {
	    PersistenceManager pm = PersistenceFactory.get()
		    .getPersistenceManager();
	    try {
		bookmark.setUserEmail(user.getEmail());
		pm.makePersistent(bookmark);
		return bookmark.getId();
	    } finally {
		pm.close();
	    }
	} else {
	    throw new NotLoggedInException();
	}
    }

    @Override
    public void deleteBookmark(Long id) throws NotLoggedInException {
	User user = UserFactory.get().getCurrentUser();
	if (user != null) {
	    PersistenceManager pm = PersistenceFactory.get()
		    .getPersistenceManager();
	    try {
		Bookmark bookmark = (Bookmark) pm.getObjectById(pm
			.newObjectIdInstance(Bookmark.class, id));
		pm.deletePersistent(bookmark);
	    } finally {
		pm.close();
	    }
	} else {
	    throw new NotLoggedInException();
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Bookmark> getBookmarks() throws NotLoggedInException {
	User user = UserFactory.get().getCurrentUser();
	if (user != null) {
	    PersistenceManager pm = PersistenceFactory.get()
		    .getPersistenceManager();
	    try {
		Query query = pm.newQuery(Bookmark.class);
		query.setFilter("userEmail == id");
		query.declareParameters("String id");

		List<Bookmark> results = (List<Bookmark>) query.execute(user
			.getEmail());
		ArrayList<Bookmark> bookmarks = new ArrayList<Bookmark>();
		for (Bookmark bookmark : results) {
		    bookmarks.add(bookmark);
		}
		return bookmarks;
	    } finally {
		pm.close();
	    }
	} else {
	    throw new NotLoggedInException();
	}
    }

    @Override
    public String isLogged(String location) {
	User user = UserFactory.get().getCurrentUser();
	if (user == null) {
	    return UserFactory.get().createLoginURL(location);
	} else {
	    return "";
	}
    }
}
