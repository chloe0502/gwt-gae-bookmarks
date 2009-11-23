package com.chrome.extension.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.search.client.ExpandMode;
import com.google.gwt.search.client.ImageResult;
import com.google.gwt.search.client.ImageSearch;
import com.google.gwt.search.client.ImageSizeValue;
import com.google.gwt.search.client.SearchCompleteHandler;
import com.google.gwt.search.client.SearchControl;
import com.google.gwt.search.client.SearchControlOptions;
import com.google.gwt.search.client.SearchUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Image search popup.
 * 
 * @author Gal Dolber
 */
public class ImagesSearchPopup extends PopupPanel implements
	HasValueChangeHandlers<String>, ClickHandler, KeyPressHandler,
	FocusHandler, BlurHandler, ValueChangeHandler<String> {

    /**
     * Style.
     * 
     * @author Gal Dolber
     */
    public static interface ImagesSearchPopupStyle extends CssResource {
	String image();

	String inlineTextbox();

	String textbox();
    }

    interface ImagesSearchPopupUiBinder extends
	    UiBinder<Widget, ImagesSearchPopup> {
    }

    private static ImagesSearchPopupUiBinder uiBinder = GWT
	    .create(ImagesSearchPopupUiBinder.class);

    /**
     * Image search.
     */
    private ImageSearch imageSearch;

    /**
     * Search handler.
     */
    private SearchCompleteHandler searchCompleteHandler = new SearchCompleteHandler() {
	@Override
	public void onSearchComplete(SearchCompleteEvent event) {
	    ImageResult result = (ImageResult) event.getResult();
	    final Image imageItem = new Image(result.getThumbnailUrl());
	    imageItem.setStyleName(style.image());
	    imageItem.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    url.setText(imageItem.getUrl());
		    ValueChangeEvent.fire(ImagesSearchPopup.this, imageItem
			    .getUrl());
		}
	    });
	    panel.add(imageItem);
	}
    };

    /**
     * Search control.
     */
    private SearchControl control;

    @UiField
    TextBox query;

    @UiField
    Button go;

    @UiField
    ImagesSearchPopupStyle style;

    @UiField
    HorizontalPanel panel;

    @UiField
    TextBox url;

    public ImagesSearchPopup() {
	super(true);
	init();
	setWidget(uiBinder.createAndBindUi(this));
	query.addKeyPressHandler(this);
	go.addClickHandler(this);
	url.addFocusHandler(this);
	url.addBlurHandler(this);
	url.addValueChangeHandler(this);
    }

    @Override
    public HandlerRegistration addValueChangeHandler(
	    ValueChangeHandler<String> handler) {
	return addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public void onBlur(BlurEvent event) {
	url.setStyleName(style.inlineTextbox());
    }

    @Override
    public void onClick(ClickEvent event) {
	search();
    }

    @Override
    public void onFocus(FocusEvent event) {
	url.setStyleName(style.textbox());
    }

    @Override
    public void onKeyPress(KeyPressEvent event) {
	if (event.getCharCode() == KeyCodes.KEY_ENTER) {
	    search();
	}
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
	ValueChangeEvent.fire(ImagesSearchPopup.this, event.getValue());
    }

    /**
     * Initialize.
     */
    private void init() {
	SearchUtils.loadSearchApi(new Runnable() {

	    public void run() {
		// Google search
		SearchControlOptions options = new SearchControlOptions();
		imageSearch = new ImageSearch();
		options.add(imageSearch, ExpandMode.OPEN);
		imageSearch.setImageSize(ImageSizeValue.MEDIUM);

		// Search control
		control = new SearchControl(options);
		control.addSearchCompleteHandler(searchCompleteHandler);
	    }
	});
    }

    /**
     * Execute search.
     */
    private void search() {
	panel.clear();
	control.execute(query.getValue());
    }
}
