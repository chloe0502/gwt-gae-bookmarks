package com.chrome.extension.client.widgets;

import com.chrome.extension.client.AddBookmarkView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Add bookmark view widget.
 * 
 * @author Gal Dolber
 */
public class AddBookmarkViewImpl extends PopupPanel implements AddBookmarkView,
	KeyPressHandler {

    /**
     * Style.
     * 
     * @author Gal Dolber
     */
    public static interface AddBookmarkViewImplStyle extends CssResource {
	String error();

	String errorHidden();
    }

    interface AddBookmarkViewImplUiBinder extends
	    UiBinder<Widget, AddBookmarkViewImpl> {
    }

    private static final String HTTP = "http://";

    private static AddBookmarkViewImplUiBinder uiBinder = GWT
	    .create(AddBookmarkViewImplUiBinder.class);

    /**
     * Key enter handler.
     */
    private KeyPressHandler enterHandler = new KeyPressHandler() {
	@Override
	public void onKeyPress(KeyPressEvent event) {
	    if (event.getCharCode() == KeyCodes.KEY_ENTER) {
		addBookmark();
	    }
	}
    };

    private Handlers handlers;

    @UiField
    Button add;

    @UiField
    Anchor cancel;

    @UiField
    HorizontalPanel buttons;

    @UiField
    TextBox name;

    @UiField
    ImageSelectBox image;

    @UiField
    TextBox url;

    @UiField
    AddBookmarkViewImplStyle style;

    @UiField
    HTML urlError;

    public AddBookmarkViewImpl() {
	setWidget(uiBinder.createAndBindUi(this));

	name.addKeyPressHandler(enterHandler);
	url.addKeyPressHandler(enterHandler);
	url.addKeyPressHandler(this);

	buttons.setCellHorizontalAlignment(add,
		HasHorizontalAlignment.ALIGN_CENTER);
	buttons.setCellHorizontalAlignment(cancel,
		HasHorizontalAlignment.ALIGN_CENTER);
	buttons
		.setCellVerticalAlignment(add,
			HasVerticalAlignment.ALIGN_MIDDLE);
	buttons.setCellVerticalAlignment(cancel,
		HasVerticalAlignment.ALIGN_MIDDLE);
    }

    @UiHandler("add")
    public void onAdd(ClickEvent event) {
	addBookmark();
    }

    @UiHandler("cancel")
    public void onCancel(ClickEvent event) {
	hide();
    }

    @Override
    public void onKeyPress(KeyPressEvent event) {
	checkForError();
    }

    /**
     * @param handlers
     *            the handlers to set
     */
    @Override
    public void setHandlers(Handlers handlers) {
	this.handlers = handlers;
    }

    @Override
    public void showPopup() {
	reset();
	center();
	show();
	name.setFocus(true);
    }

    /**
     * Adds the bookmark.
     */
    private void addBookmark() {
	// Valid fields
	if (checkForError()) {
	    handlers.onAddBookmark(name.getText(), image.getUrl(), url
		    .getText());
	    hide();
	}
    }

    /**
     * Check errors.
     */
    private boolean checkForError() {
	boolean isUrl = isUrl(url.getText());
	if (isUrl) {
	    urlError.setStyleName(style.errorHidden());
	} else {
	    urlError.setStyleName(style.error());
	}
	return isUrl;
    }

    /**
     * Validate url.
     * 
     * @param url
     * @return
     */
    private native boolean isUrl(String url)/*-{
					    var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/
					    return regexp.test(url);
					    }-*/;

    /**
     * Reset the field.
     */
    private void reset() {
	name.setText("");
	url.setText(HTTP);
	image.clear();
	urlError.setStyleName(style.errorHidden());
    }
}
