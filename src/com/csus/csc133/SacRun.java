package com.csus.csc133;

import java.util.Date;
import java.util.Random;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.util.DateUtil;
import com.csus.csc133.models.GameModel;
import com.csus.csc133.models.GameObject;
import com.csus.csc133.models.Lecture;
import com.csus.csc133.models.LectureHall;
import com.csus.csc133.models.Restroom;
import com.csus.csc133.models.Student;
import com.csus.csc133.models.StudentAngry;
import com.csus.csc133.models.StudentPlayer;
import com.csus.csc133.models.WaterDispenser;
import com.csus.csc133.views.ViewMap;
import com.csus.csc133.views.ViewMessage;
import com.csus.csc133.views.ViewStatus;

public class SacRun extends Form implements ActionListener<ActionEvent> {

	private GameModel gm;

	ViewStatus viewStatus;
	ViewMessage viewMessage;
	ViewMap viewMap;

	public SacRun() {
		gm = new GameModel();

		gm.init();
		viewStatus = new ViewStatus();
		viewMessage = new ViewMessage();
		viewMap = new ViewMap();

		Toolbar toolbar = new Toolbar();
		this.setToolbar(toolbar);

		Command changeStrategiesCommand = new Command("Change Strategies") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				gm.setData(gm.changeStrategy());
				gm.setData("Changed Strategy");
			}
		};

		toolbar.addCommandToSideMenu(changeStrategiesCommand);

		Command aboutCommand = new Command("About") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Dialog.show("About", "This is my app!", "OK", null);
			}
		};
		toolbar.addCommandToSideMenu(aboutCommand);

		Command exitCommand = new Command("Exit") {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Display.getInstance().exitApplication();
			}
		};
		toolbar.addCommandToSideMenu(exitCommand);

		// five areas:
		// East for game information
		// West for commands
		// center for game map
		// south for message box
		// North -> None
		// View status - > East
		// View message -> south
		// View map -> center

		// UI provided for A1 only, remove it in A2
//		this.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());

		Style blueButtonStyle = new Style();
		blueButtonStyle.setBgColor(0x0000ff);
		blueButtonStyle.setFgColor(0xffffff);
		blueButtonStyle.setBorder(Border.createLineBorder(1, 0x000000));

		Container btnContainer = new Container();
		btnContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

		Button btnMove = new Button("Move");
		btnMove.addActionListener(this);

		Button btnStop = new Button("Stop");
		btnStop.addActionListener(this);

		Button btnTurnLeft = new Button("Turn Left");
		btnTurnLeft.addActionListener(this);

		Button btnTurnRIght = new Button("Turn Right");
		btnTurnRIght.addActionListener(this);

		Button btnChange = new Button("Change Strategies");
		btnChange.addActionListener(this);

		Button btnHall = new Button("Lecture Hall");
		btnHall.addActionListener(this);

		Button btnRestroom = new Button("Restroom");
		btnRestroom.addActionListener(this);

		Button btnDispenser = new Button("Water Dispenser");
		btnDispenser.addActionListener(this);

		Button btnStudent = new Button("Student");
		btnStudent.addActionListener(this);

		Button btnNextFrame = new Button("Next Frame");
		btnNextFrame.addActionListener(this);

		btnContainer.add(btnMove);
		btnContainer.add(btnStop);
		btnContainer.add(btnTurnLeft);
		btnContainer.add(btnTurnRIght);
		btnContainer.add(btnChange);
		btnContainer.add(btnHall);
		btnContainer.add(btnRestroom);
		btnContainer.add(btnDispenser);
		btnContainer.add(btnStudent);
		btnContainer.add(btnNextFrame);

		for (int i = 0; i < btnContainer.getChildrenAsList(false).size(); i++) {
			btnContainer.getComponentAt(i).setUnselectedStyle(blueButtonStyle);
		}

		this.add(BorderLayout.WEST, btnContainer);
		this.add(BorderLayout.SOUTH, viewStatus);
		this.add(BorderLayout.CENTER, viewMap);
		this.add(BorderLayout.EAST, viewMessage);

		gm.addObserver(viewStatus);
		gm.addObserver(viewMap);
		gm.addObserver(viewMessage);

		this.show();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Button sourceButton = (Button) event.getSource();
		String command = sourceButton.getText();
		switch (command) {
		case "Move":
			startMoving();
			break;
		case "Stop":
			stopMoving();
			break;
		case "Turn Left":
			turnLeft();
			break;
		case "Turn Right":
			turnRight();
			break;
		case "Lecture Hall":
			collideLectureHall();
			break;
		case "Restroom":
			collideRestRoom();
			break;
		case "Water Dispenser":
			collideWaterDispenser();
			break;
		case "Student":
			collideNonStudent();
			break;
		case "Next Frame":
			moveNextFrame();
			break;
		case "Change Strategies":
			changeStrategy();
			break;
		default:
			gm.setData("Invalid Command");
			break;
		}
		displayGameState();

	}

	// ‘w’ – Ask the StudentPlayer to start moving.
	void startMoving() {
		gm.setData("Starting to move");
		for (GameObject obj : gm.gameObjects) {
			if (obj instanceof StudentPlayer) {
				gm.setData("Moving Student player");
				((StudentPlayer) obj).startMoving();
				gm.setData("Moved student player");
				return;

			}
		}

	}

	// ‘s’ – Ask the StudentPlayer to stop moving.
	void stopMoving() {
		gm.setData("Stoping to move");
		for (GameObject obj : gm.gameObjects) {
			if (obj instanceof StudentPlayer) {
				((StudentPlayer) obj).stopMoving();
				gm.setData("Student player stopped");
				return;
			}
		}

	}

	// ‘a’ – Ask the StudentPlayer to turn left.
	void turnLeft() {
		gm.setData("Turning left");
		for (GameObject obj : gm.gameObjects) {
			if (obj instanceof StudentPlayer) {
				((StudentPlayer) obj).turnLeft();
				gm.setData("Student player turned left");
				return;
			}
		}

	}

	// ‘d’ – Ask the StudentPlayer to turn right.
	void turnRight() {
		gm.setData("Turning right");
		for (GameObject obj : gm.gameObjects) {
			if (obj instanceof StudentPlayer) {
				((StudentPlayer) obj).turnRight();
				gm.setData("Student player turned right");
				return;
			}
		}

	}

	// ‘1’ – Pretend there is collision between StudentPlayer and LectureHall,
	void collideLectureHall() {
		for (GameObject obj : gm.gameObjects) {
			if (obj instanceof StudentPlayer) {
				for (GameObject building : gm.gameObjects) {
					if (building instanceof LectureHall) {
						for (Lecture lecture : gm.lectures) {
							if (((LectureHall) building).name == lecture.getHall().name) {
								lecture.setEndTime(new Date());
								gm.setData("Collided with lecture hall");
							}
						}
					}
				}
			}
		}

	}

	// ‘2’ – Pretend there is collision between StudentPlayer and Restroom.
	void collideRestRoom() {
		for (GameObject obj : gm.gameObjects) {
			if (obj instanceof StudentPlayer) {
				for (GameObject building : gm.gameObjects) {
					if (building instanceof Restroom) {
						((StudentPlayer) obj).clearWater();
						gm.setData("Collided with rest room");
						return;
					}
				}
			}
		}

	}

	// ‘3’ – Pretend there is collision between StudentPlayer and WaterDispenser
	void collideWaterDispenser() {
		for (GameObject obj : gm.gameObjects) {
			if (obj instanceof StudentPlayer) {
				for (GameObject building : gm.gameObjects) {
					if (building instanceof WaterDispenser) {
						((StudentPlayer) obj).drinkWater();
						gm.setData("Collided with Water dispenser");
						return;
					}
				}
			}
		}

	}

	// ‘4’ – You need to randomly pick a non-player Student instance in the
	// GameModel, and then pretend there is
	// collision between StudentPlayer and this selected Student.
	void collideNonStudent() {
		for (GameObject obj : gm.gameObjects) {
			if (obj instanceof StudentPlayer) {
				for (GameObject player : gm.gameObjects) {
					if (player instanceof StudentAngry) {
						((StudentPlayer) obj).handleCollide((Student) player);
						gm.setData("Collided with Student");
						return;
					}
				}
			}
		}

	}

	// ‘f’ – Tell the GameModel to go to the next Frame
	void moveNextFrame() {
		gm.gametime.setTime(gm.gametime.getTime() + 1);
		gm.setData("Moving to next frame");
		for (GameObject obj : gm.gameObjects) {
			obj.move();
		}

		Lecture lec = null;

		for (Lecture lecture : gm.lectures) {
			if (lecture.getEndTime().getTime() > new Date().getTime()) {
				Random rand = new Random();
				int val = rand.nextInt(11);
				if (val > 8) {
					// add new lecture
					lec = new Lecture((LectureHall) gm.gameObjects.get(7), new Date());

				}
			} else {
				((StudentPlayer) (gm.gameObjects.get(0)))
						.setAbsenceTime(((StudentPlayer) (gm.gameObjects.get(0))).getAbsenceTime() + 1);
			}
		}

		if (lec != null) {
			gm.lectures.add(lec);
		}
		StudentPlayer player = (StudentPlayer) (gm.gameObjects.get(0));
		if (player.getAbsenceTime() > 10 || player.getHydration() < 80 || player.getWaterIntake() > 20) {
			gm.setData("Game over");
			gm.setData("Time : " + String.valueOf(gm.gametime.getTime()));
		}

	}

	//
	void displayGameState() {
		String state = "Game state";
		state +="Time : " + String.valueOf(gm.gametime.getTime());
		for (GameObject obj : gm.gameObjects) {
			state+=obj.toString();
		}
		
		gm.getState().setMessage(state);
		viewMap.update(gm, gm.getState());		
		
	}

	void changeStrategy() {
		gm.setData(gm.changeStrategy());
		gm.setData("Changed Strategy");
	}

}
