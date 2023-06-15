package com.csus.csc133.models;

public class StudentFriendly extends Student{
	
    // default constructor
    public StudentFriendly(int x, int y) {
        super(x, y);
        talkingTime = (int) (DEFAULT_TALKINGTIME*1.0/2);
    }
}
