package com.csus.csc133.models;

public class StudentNonstop extends Student{
	
    // default constructor
    public StudentNonstop(int x, int y) {
        super(x, y);
    }

    @Override
    public void handleCollide(Student s) {
        // do nothing
    }
}
