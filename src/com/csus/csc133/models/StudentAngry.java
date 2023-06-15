package com.csus.csc133.models;

public class StudentAngry extends Student {
	
    // default constructor
    public StudentAngry(int x, int y) {
        super(x, y);
        talkingTime = DEFAULT_TALKINGTIME*2;
    }
}
