package com.csus.csc133.models;

public class StudentStrategy extends Student {
    private Student movementStrategy;

    public StudentStrategy(int x, int y, Student movementStrategy) {
        super(x, y);
        this.movementStrategy = movementStrategy;
    }

    public void setMovementStrategy(Student movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
    
    public Student getStudentType() {
    	return movementStrategy;
    }
    
    public String getMovementStrategy() {
    	return movementStrategy.getClass().getSimpleName();
    }

    @Override
    public void move() {
        movementStrategy.move();
    }

}
