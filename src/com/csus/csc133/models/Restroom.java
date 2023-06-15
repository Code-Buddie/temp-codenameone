package com.csus.csc133.models;

public class Restroom extends Building{
	
    // default constructor
    public Restroom( int x, int y) {
        super(x, y);
    }

    @Override
    void handleCollide(Student s) {
        s.clearWater();
    }
    
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + " \n" + x +"," +y ;
    }
}
