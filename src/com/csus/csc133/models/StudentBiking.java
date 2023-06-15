package com.csus.csc133.models;

public class StudentBiking extends Student{
	
    // default constructor
    public StudentBiking(int x, int y) {
        super(x, y);
        speed = DEFAULT_SPEED*3;
    }
}
