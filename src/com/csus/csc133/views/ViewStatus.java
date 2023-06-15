package com.csus.csc133.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.csus.csc133.models.GameState;

public class ViewStatus extends Container implements Observer {
	private Label messageLabel;

	public ViewStatus() {
		messageLabel = new Label("");
		this.add(messageLabel);
	}

	@Override
	public void update(Observable observable, Object data) {
		if (data instanceof GameState) {
			GameState state = (GameState) data;
			messageLabel.setText(state.toString());
		}
	}

}
