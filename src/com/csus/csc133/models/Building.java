package com.csus.csc133.models;

abstract class Building extends GameObject {

    // default constructor
    public Building(int x, int y) {
        super(x,y);
    }

    @Override
    public void move() {
        // Do nothing
    }
    
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + "\n" + x +"," +y ;
    }
}
