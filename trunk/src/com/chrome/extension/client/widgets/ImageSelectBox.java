package com.chrome.extension.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

/**
 * Image select box with google search api.
 * 
 * @author Gal Dolber
 */
public class ImageSelectBox extends Composite implements ClickHandler,
	ValueChangeHandler<String> {

    private ImagesSearchPopup popup = new ImagesSearchPopup();

    private Image image = new Image();

    public ImageSelectBox() {
	initWidget(image);
	image.addClickHandler(this);

	popup.addValueChangeHandler(this);
    }

    /**
     * Set url.
     * 
     * @param string
     */
    public void clear() {
	image
		.setUrl("http://t0.gstatic.com/images?q=tbn:InPIgnEdZBj-9M:http://pregrado.virtualepn.edu.ec/file.php/1/estrella-dorada.jpg");
    }

    /**
     * @return url
     */
    public String getUrl() {
	return image.getUrl();
    }

    @Override
    public void onClick(ClickEvent event) {
	popup.showRelativeTo(image);
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
	image.setUrl(event.getValue());
    }
}
