package com.chrome.extension.client;

import com.gwt.framework.client.View;

/**
 * Main view.
 * 
 * @author Gal Dolber
 */
public interface MainView extends View {

    void add(BookmarkView view);

    void reset();
}
