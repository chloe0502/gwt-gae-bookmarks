package com.chrome.extension.client.widgets;

import com.chrome.extension.client.AddBookmarkPresenter;
import com.chrome.extension.client.BookmarkView;
import com.chrome.extension.client.MainView;
import com.chrome.extension.client.gin.GwtInjector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Main view impl.
 * 
 * @author Gal Dolber
 */
public class MainViewImpl extends Composite implements MainView, ResizeHandler {

    interface MainViewImplUiBinder extends UiBinder<Widget, MainViewImpl> {
    }
    
    private static int horizontalNumber;

    private static MainViewImplUiBinder uiBinder = GWT
	    .create(MainViewImplUiBinder.class);

    @UiField
    FlowPanel bookmarks;

    // We already have to add button
    private int count = 1;

    @UiField
    HorizontalPanel horizontal;

    @UiField
    BookmarkViewImpl add;

    private AddBookmarkPresenter addBookmarkPresenter = GwtInjector.INJECTOR
	    .getAddBookmarkPresenter();

    public MainViewImpl() {
	initWidget(uiBinder.createAndBindUi(this));
	
	onResize(null);
	
	Window.addResizeHandler(this);
    }

    @Override
    public void add(BookmarkView view) {
	if (count == 0) {
	    horizontal = new HorizontalPanel();
	    bookmarks.add(horizontal);
	    count++;
	} else if (count == horizontalNumber - 1) {
	    count = 0;
	} else {
	    count++;
	}
	horizontal.add((Widget) view);
    }

    @UiFactory
    public BookmarkViewImpl getAddButton() {
	BookmarkView view = GwtInjector.INJECTOR.getBookmarkView();
	view
		.setImageUrl("http://viewsourcecode.org/images/admin-icons/add.png");
	view.setLabel("Add");
	view.setHandlers(new BookmarkView.Handlers() {
	    @Override
	    public boolean canDelete() {
		return false;
	    }

	    @Override
	    public void onClick() {
		addBookmarkPresenter.getView().showPopup();
	    }

	    @Override
	    public void onDelete() {
	    }
	});
	return (BookmarkViewImpl) view;
    }

    @Override
    public void reset() {
	horizontal = new HorizontalPanel();
	horizontal.add(add);
	bookmarks.clear();
	bookmarks.add(horizontal);
	count = 1;
    }

    @Override
    public void onResize(ResizeEvent event) {
	 horizontalNumber = RootPanel.getBodyElement().getOffsetWidth() / 74;
    }
}
