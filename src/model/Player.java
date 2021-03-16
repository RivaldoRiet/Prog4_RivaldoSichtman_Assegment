package model;

import java.util.Observable;

public class Player extends Observable {
    // keeps the score and the lives of the player
	private int score;
	private int lives;

	public Player() {
		//sets the default value
		score = 0;
		lives = 3;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		//notifies the score has changed and sets the score
		this.score = score;
		this.setChanged();
		this.notifyObservers(this);
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		//notifies the lives have changed and sets the score
		this.lives = lives;
		this.setChanged();
		this.notifyObservers(this);
	}

}
