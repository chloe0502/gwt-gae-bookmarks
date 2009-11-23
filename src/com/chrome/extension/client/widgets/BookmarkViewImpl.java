package com.chrome.extension.client.widgets;

import com.chrome.extension.client.BookmarkView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Bookmark widget.
 * 
 * @author Gal Dolber
 */
public class BookmarkViewImpl extends Composite implements BookmarkView {

    /**
     * Boolmark style.
     * 
     * @author Gal Dolber
     */
    interface BookmarkStyle extends CssResource {
	String deleteHide();

	String deleteOver();

	String deleteVisible();

	String down();

	String image();

	String label();

	String up();
    }

    interface BookmarkViewUiBinder extends UiBinder<Widget, BookmarkViewImpl> {
    }

    private static BookmarkViewUiBinder uiBinder = GWT
	    .create(BookmarkViewUiBinder.class);

    @UiField
    BookmarkStyle style;

    @UiField
    FlowPanel button;

    @UiField
    Image image;

    @UiField
    Label label;

    @UiField
    Label delete;

    private Handlers handlers;

    public BookmarkViewImpl() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setHandlers(Handlers handlers) {
	this.handlers = handlers;
    }

    @Override
    public void setImageUrl(String url) {
	image.setUrl(url);
    }

    @Override
    public void setLabel(String text) {
	label.setTitle(text);
	label.setText(text);
    }

    @UiHandler("button")
    void onClick(ClickEvent e) {
	handlers.onClick();
    }

    @UiHandler("delete")
    void onClickDelete(ClickEvent e) {
	e.stopPropagation();
	handlers.onDelete();
    }

    @UiHandler("button")
    void onMouseOut(MouseOutEvent event) {
	button.setStyleName(style.up());
	delete.setStyleName(style.deleteHide());
    }

    @UiHandler("delete")
    void onMouseOutDelete(MouseOutEvent event) {
	delete.setStyleName(style.deleteVisible());
    }

    @UiHandler("button")
    void onMouseOver(MouseOverEvent event) {
	button.setStyleName(style.down());
	if (handlers.canDelete()) {
	    delete.setStyleName(style.deleteVisible());
	}
    }

    @UiHandler("delete")
    void onMouseOverDelete(MouseOverEvent event) {
	delete.setStyleName(style.deleteOver());
    }
}
