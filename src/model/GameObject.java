package model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {

	private int locationX = 0;
	private int locationY = 0;
	private int worldheight;
	private int worldwidth;
	private boolean isOutOfScreen = false;
	private final int moveSize = 10;
	private BufferedImage img;
	private String fruitName;
	private String direction;
	// protected so the subclasses have access to change the size
	protected int size;

	public GameObject(int worldwidth, int worldheight, String direction, String fruitName) {
		this.worldwidth = worldwidth;
		this.worldheight = worldheight;
		this.direction = direction;
		this.fruitName = fruitName;
		directionToLocation();
	}

	// the way the objects get their score is defined in their own class
	public abstract int getScore();

	
	//converts the given direction to a new location for the object
	private void directionToLocation() {
		if (direction.equalsIgnoreCase("left")) {
			locationX = worldwidth - worldwidth;
			locationY = (int) (Math.random() * (worldheight));

		}
		if (direction.equalsIgnoreCase("right")) {
			locationX = worldwidth - size;
			locationY = (int) (Math.random() * (worldheight));

		}
		if (direction.equalsIgnoreCase("up")) {
			locationX = (int) (Math.random() * (worldwidth));
			locationY = worldheight - worldheight;

		}
		if (direction.equalsIgnoreCase("down")) {
			locationX = (int) (Math.random() * (worldwidth));
			locationY = worldheight - size;
		}
	}

	//moves the object by looking at its current direction and checks if it is on the screen
	public void move() {
		if (getLocationX() < 0 || getLocationX() > worldwidth || getLocationY() < 0 || getLocationY() > worldheight) {
			isOutOfScreen = true;
		} else {
			isOutOfScreen = false;
			if (getDirection().equalsIgnoreCase("right")) {
				setLocationX(getLocationX() - moveSize);
			}

			if (getDirection().equalsIgnoreCase("left")) {
				setLocationX(getLocationX() + moveSize);
			}

			if (getDirection().equalsIgnoreCase("up")) {
				setLocationY(getLocationY() + moveSize);
			}

			if (getDirection().equalsIgnoreCase("down")) {
				setLocationY(getLocationY() - moveSize);
			}
		}
	}

	public String getDirection() {
		return direction;
	}

	public boolean isOutOfScreen() {
		return isOutOfScreen;
	}
	
	public void objectIntersected() {
		isOutOfScreen = true;
	}

	public Rectangle getBounds() {
		return new Rectangle(getLocationX(), getLocationY(), getSize(), getSize());
	}

	public BufferedImage getImg() {
		return img;
	}

	public int getSize() {
		return size;
	}

	public String getFruitName() {
		return fruitName;
	}

	public void setImage(BufferedImage img) {
		this.img = img;
	}

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}

}
