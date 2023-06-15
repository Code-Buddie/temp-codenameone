package com.csus.csc133.models;

import java.util.Random;

public class Student extends GameObject {
    public final int DEFAULT_SPEED = 20;
    public final int DEFAULT_TALKINGTIME = 2;

    int head;
    int speed;
    int talkingTime;
    int timeRemain;
    private int hydration;
    private int waterIntake;
    int sweatingRate;
    private int absenceTime;

    // default constructor
    public Student(int x, int y) {
        super(x, y);
        Random random = new Random();
        head = random.nextInt(360);
        speed = DEFAULT_SPEED;
        talkingTime = DEFAULT_TALKINGTIME;
        timeRemain = 0;
        setHydration(100);
        setWaterIntake(0);
        sweatingRate = 1;
        setAbsenceTime(0);
    }

    public void drinkWater() {
        setWaterIntake(getWaterIntake() + 100 - getHydration());
        setHydration(100);
    }

    public void clearWater() {
        setWaterIntake(0);
    }

    @Override
    public void move() {
        if (timeRemain >= 0) {
            x = (int) (x + Math.cos(Math.toRadians(90 - head)) * speed);
            y = (int) (y + Math.sin(Math.toRadians(90 - head)) * speed);
            setHydration(getHydration() - sweatingRate);
        }
    }

    @Override
	public void handleCollide(Student s) {
        int max = s.talkingTime;
        if (this.talkingTime > s.talkingTime) {
            max = this.talkingTime;
        }

        s.timeRemain = max;
        this.timeRemain = max;
    }
    
    @Override
    public String toString() {
    	// , Position, head, speed, Hydration, timeRemain
    	return this.getClass().getSimpleName() + " \n" + x +"," +y +" head: " + head + " speed: " + speed + " hydration "+ getHydration() + " time remaining "+timeRemain;
    }

	public int getAbsenceTime() {
		return absenceTime;
	}

	public void setAbsenceTime(int absenceTime) {
		this.absenceTime = absenceTime;
	}

	public int getHydration() {
		return hydration;
	}

	public void setHydration(int hydration) {
		this.hydration = hydration;
	}

	public int getWaterIntake() {
		return waterIntake;
	}

	public void setWaterIntake(int waterIntake) {
		this.waterIntake = waterIntake;
	}

}
