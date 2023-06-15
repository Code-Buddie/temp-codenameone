package com.csus.csc133.models;

public class WaterDispenser extends Building{
	
    // default constructor
    public WaterDispenser(int x, int y) {
        super(x, y);
    }

    @Override
    public void handleCollide(Student s) {
        s.drinkWater();
    }
}
