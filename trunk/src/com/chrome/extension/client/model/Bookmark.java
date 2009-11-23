package com.chrome.extension.client.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Bookmark.
 * 
 * @author Gal Dolber
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Bookmark implements IsSerializable {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    String userEmail;

    @Persistent
    private String url;

    @Persistent
    private String icon;

    @Persistent
    private String name;

    public Bookmark() {
    }

    public Bookmark(String url, String icon, String name) {
	this.url = url;
	this.icon = icon;
	this.name = name;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
	return icon;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }

    /**
     * @return the user email
     */
    public String getUserEmail() {
	return userEmail;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon) {
	this.icon = icon;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }

    /**
     * @param userEmail
     *            the id to set
     */
    public void setUserEmail(String userEmail) {
	this.userEmail = userEmail;
    }
}