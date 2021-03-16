package model;

import java.awt.geom.Point2D;
import java.util.Observable;
import java.util.Random;



//creates and handles the game objects
public class PlayingField extends Observable implements Runnable {

	private int width;
	private int height;
	private GameObject gameObject;
	private Random rn = new Random();
	private DIR direction;
	private String directionString;
	private int smallFruit = 30;
	private int bigFruit = 50;
	private SlashTrailSection currentSlice;
	private Boolean timeIsUp = false;
	private enum DIR {
		UP, DOWN, LEFT, RIGHT
	};
	// get the start time
	private long start = System.currentTimeMillis();
	// 60 seconds from now
	private long end = start + 50 * 1000;

	public PlayingField(int worldWidth, int worldHeight) {
		// slashTrailSection = new SlashTrailSection();
		this.width = worldWidth;
		this.height = worldHeight;
		createObject();
	}

	public void createSlice(Point2D p) {
		/* creates a slice and gives the object to the view by notifying it */
		// the view paints the object while it exists and updates the given
		// coordinates
		currentSlice = new SlashTrailSection();
		currentSlice.setStartX((int) p.getX());
		currentSlice.setStartY((int) p.getY());
		currentSlice.setEndX((int) p.getX());
		currentSlice.setEndY((int) p.getY());
		this.setChanged();
		this.notifyObservers(currentSlice);
	}

	@Override
	public void run() {
		while (true) {

			if (timeIsUp == false) {
				if (System.currentTimeMillis() < end) {
					timeIsUp = false;
				} else {
					//check if the time is up and if yes notify the view
					timeIsUp = true;
					this.setChanged();
					this.notifyObservers(timeIsUp);
				}
			}

			// aslong as there is a gameobject on screen it calls the move
			// method
			// a game object is on screen aslong as it isn't slashed and the
			// coordinates are not out of the screen
			if (!timeIsUp) {
				if (!gameObject.isOutOfScreen()) {
					if (gameObject != null) {
						gameObject.move();
						this.setChanged();
						this.notifyObservers(gameObject);
					}
				} else {
					// if there isn't a gameobject on screen it will create a
					// new one
					gameObject = null;
					createObject();
				}
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	private void createObject() {

		/*
		 * Random number between 1 and 4 that we later use to pick a random
		 * fruit type
		 */
		int answer = rn.nextInt(4) + 1;
		// get a random direction out of the enum list
		this.direction = DIR.values()[(int) (Math.random() * DIR.values().length)];
		directionString = this.direction.toString();

		// the random number gets translated into a new gameobject
		switch (answer) {
		case 1:
			gameObject = new Bomb(width, height, directionString, "bomb");
			break;
		case 2:
			gameObject = new Fruit(width, height, directionString, "apple", bigFruit);
			break;
		case 3:
			gameObject = new Fruit(width, height, directionString, "orange", bigFruit);
			break;
		case 4:
			gameObject = new Fruit(width, height, directionString, "strawberry", smallFruit);
			break;

		default:
			break;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public SlashTrailSection getCurrentSlice() {
		return currentSlice;
	}

}
