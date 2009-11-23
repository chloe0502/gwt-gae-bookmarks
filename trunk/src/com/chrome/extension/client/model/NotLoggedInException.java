package com.chrome.extension.client.model;

import java.io.Serializable;

/**
 * Not logged exception.
 * 
 * @author Gal Dolber
 */
public class NotLoggedInException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    public NotLoggedInException() {
	super();
    }

    public NotLoggedInException(String message) {
	super(message);
    }
}