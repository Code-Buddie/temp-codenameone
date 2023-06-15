package com.csus.csc133.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class GameModel extends Observable {

	private int width, height;
	public Date gametime;

	public List<GameObject> gameObjects;
	public List<Lecture> lectures;

	StudentStrategy studentStrategy1;
	StudentStrategy studentStrategy2;
	
	private String status;
	private GameState gameState;

	// default constructor
	public GameModel() {
		width = 1024;
		height = 768;
		gameObjects = new ArrayList<>();
		lectures = new ArrayList<>();
	}

	public void init() {
		gametime = new Date();
		
		gameState = new GameState("");

		// place objects in random positions
		// StudentPlayer, StudentAngry, StudentBiking, StudentCar,
		// StudentFriendly, StudentHappy, StudentRunning, LectureHall, Restroom, and
		// WaterDispenser

		Random rand = new Random();
		StudentPlayer studentPlayer = new StudentPlayer(rand.nextInt(width), rand.nextInt(height));
		studentStrategy1 = new StudentStrategy(rand.nextInt(width), rand.nextInt(height), studentPlayer);

		StudentAngry studentAngry = new StudentAngry(rand.nextInt(width), rand.nextInt(height));

		StudentBiking studentBiking = new StudentBiking(rand.nextInt(width), rand.nextInt(height));

		StudentCar studentCar = new StudentCar(rand.nextInt(width), rand.nextInt(height));

		StudentFriendly studentFriendly = new StudentFriendly(rand.nextInt(width), rand.nextInt(height));

		StudentHappy studentHappy = new StudentHappy(rand.nextInt(width), rand.nextInt(height));
		studentStrategy2 = new StudentStrategy(rand.nextInt(width), rand.nextInt(height), new StudentConfused(width, height));

		StudentRunning studentRunning = new StudentRunning(rand.nextInt(width), rand.nextInt(height));

		LectureHall lectureHall = new LectureHall("Hall One", rand.nextInt(width), rand.nextInt(height));

		lectures.add(new Lecture(lectureHall, new Date()));

		Restroom restroom = new Restroom(rand.nextInt(width), rand.nextInt(height));

		WaterDispenser dispenser = new WaterDispenser(rand.nextInt(width), rand.nextInt(height));

		gameObjects.add(studentPlayer);
		gameObjects.add(studentAngry);
		gameObjects.add(studentBiking);
		gameObjects.add(studentCar);
		gameObjects.add(studentFriendly);
		gameObjects.add(studentHappy);
		gameObjects.add(studentRunning);

		gameObjects.add(lectureHall);
		gameObjects.add(restroom);
		gameObjects.add(dispenser);

	}

	public String changeStrategy() {
		Student temp = studentStrategy1.getStudentType();
		studentStrategy1.setMovementStrategy(studentStrategy2.getStudentType());
		studentStrategy2.setMovementStrategy(temp);
		
		return studentStrategy1.getMovementStrategy();
	}
	

	public void setData(String message) {
		this.status = message;
		setChanged();
		notifyObservers(message);
	}
	
	public void setState(String state) {
		this.gameState.setMessage(state);
		setChanged();
		notifyObservers(this.gameState);
	}
	
	public GameState getState() {
		return gameState;
	}

	public String getStatus() {
		return status;
	}

}
