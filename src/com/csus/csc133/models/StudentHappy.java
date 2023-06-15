package com.csus.csc133.models;

import java.util.Random;

public class StudentHappy extends Student{
	
    // default constructor
    public StudentHappy(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        if (timeRemain >= 0) {
            Random random = new Random();
            double tempSpeed = (random.nextDouble()+1)*speed;
            x = (int) (x + Math.cos(Math.toRadians(90 - head)) * tempSpeed);
            y = (int) (y + Math.sin(Math.toRadians(90 - head)) * tempSpeed);
            setHydration(getHydration() - sweatingRate);
        }
    }
}
