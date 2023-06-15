package com.csus.csc133.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ViewMessage extends Container implements Observer {
	private Label messageLabel;

	public ViewMessage() {
		messageLabel = new Label("");
		this.add(messageLabel);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof String) {
			messageLabel.setText(data.toString());
		}
	}

}
