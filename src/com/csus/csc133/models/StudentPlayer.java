package com.csus.csc133.models;

public class StudentPlayer extends Student {
	
    // default constructor
    public StudentPlayer(int x, int y) {
        super(x, y);
    }

    public void startMoving() {
        speed = DEFAULT_SPEED;
    }

    public void stopMoving() {
        speed = 0;
    }

    public void turnLeft() {
        head += -5;
    }

    public void turnRight(){
        head+=5;
    }
    
    @Override
    public String toString() {
		return this.getClass().getSimpleName() + "\n" + x +"," +y +" head: " + head + " speed: " + speed + " hydration: "+ getHydration() + " time remaining: "+timeRemain +" absenceTime "+ getAbsenceTime() +" WaterIntake: " +getWaterIntake();

	}
}
