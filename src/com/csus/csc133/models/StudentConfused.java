package com.csus.csc133.models;

import java.util.Random;

public class StudentConfused extends Student{
	
    // default constructor
    public StudentConfused(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        if (timeRemain >= 0) {
            Random random = new Random();
            head = random.nextInt(360);
            x = (int) (x + Math.cos(Math.toRadians(90 - head)) * speed);
            y = (int) (y + Math.sin(Math.toRadians(90 - head)) * speed);
            setHydration(getHydration() - sweatingRate);
        }
    }
}
