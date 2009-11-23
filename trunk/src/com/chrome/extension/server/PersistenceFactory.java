package com.chrome.extension.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Persistence factory.
 * 
 * @author Gal Dolber
 */
public final class PersistenceFactory {

    /**
     * Instance.
     */
    private static final PersistenceManagerFactory pmfInstance = JDOHelper
	    .getPersistenceManagerFactory("transactions-optional");

    /**
     * @return Persistence manager
     */
    public static PersistenceManagerFactory get() {
	return pmfInstance;
    }
}