package com.chrome.extension.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * User factory.
 * 
 * @author Gal Dolber
 */
public class UserFactory {

    /**
     * Instance.
     */
    private static UserService userService = UserServiceFactory
	    .getUserService();

    /**
     * @return User service.
     */
    public static UserService get() {
	return userService;
    }
}
