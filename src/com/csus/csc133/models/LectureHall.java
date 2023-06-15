package com.csus.csc133.models;

public class LectureHall extends Building{
	// name of the lecture hall
    public String name;

    
    // default constructor
    public LectureHall(String name, int x, int y) {
        super(x,y);
        this.name = name;
    }

    @Override
    void handleCollide(Student s) {

    }
}
