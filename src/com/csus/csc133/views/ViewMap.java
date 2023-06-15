package com.csus.csc133.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;


public class ViewMap extends Container implements Observer {
	
	public ViewMap() {
		this.setWidth(25);
		this.setHeight(50);
		this.getStyle().setBorder(Border.createLineBorder(2, 0xff0000));
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

}
