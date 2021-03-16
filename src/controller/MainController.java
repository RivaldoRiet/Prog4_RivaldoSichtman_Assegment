package controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import javax.swing.SwingUtilities;

import model.Player;
import model.PlayingField;
import view.Gui;

public class MainController {
	private Gui gui;
	private SoundPlayer soundplayer;
	private PlayingField playingField;
	private final int WORLDWIDTH = 500;
	private final int WORLDHEIGHT = 500;
	private Player player;

	public MainController() {
		player = new Player();
		soundplayer = new SoundPlayer();
		playingField = new PlayingField(WORLDWIDTH, WORLDHEIGHT);
		gui = new Gui(this, playingField, player);

		// creates a gui runnable

		Runnable myGuiThread = new Runnable() {
			@Override
			public void run() {
				gui.create();
			}
		};
		SwingUtilities.invokeLater(myGuiThread);

		// call the music to start
		setPause(false);
	}


	public void playSlashSound() {
		soundplayer.playSlash();
	}

	public void createSlice(Point p) {
		// calls the playing field to create a new line object
		playingField.createSlice(p);
	}

	public void handleIntersection() {
		/*
		 * if the line intersects with the game object we handle the points and
		 * remove the game object we also play a slash sound
		 */
		if (intersects(playingField.getCurrentSlice().getStartX(), playingField.getCurrentSlice().getStartY(),
				playingField.getCurrentSlice().getEndX(), playingField.getCurrentSlice().getEndY())) {
			handlePoints();
			playingField.getGameObject().objectIntersected();
			playSlashSound();
		}
	}

	public void handlePoints() {
		/*
		 * checks if the next action of the player ends the game, if not and the
		 * player hits a bomb the player gets 1 life less, if the player hits
		 * fruit it checks how many score the fruit has assigned
		 */
		if (checkEndGame()) {
			handleEndGame();
		} else {
			if (playingField.getGameObject().getFruitName().equalsIgnoreCase("bomb")) {
				player.setLives(player.getLives() - 1);
			} else {
				player.setScore(player.getScore() + playingField.getGameObject().getScore());
			}
		}
	}

	public boolean checkEndGame() {
		// if the player has only 1 life left and the line hits a bomb next it
		// will return true
		if (player.getLives() == 1 && playingField.getGameObject().getFruitName().equalsIgnoreCase("bomb")) {
			return true;
		}
		return false;
	}

	public void handleEndGame() {
		// ends the game
		gui.getEndPane().setScore(player.getScore());
		setPause(true);
		gui.setEndPane();
	}

	public boolean intersects(int x, int y, int x1, int y1) {
		Point p1 = new Point(x, y);
		Point p2 = new Point(x1, y1);
		// creates a line with the given parameters
		Line2D line = new Line2D.Double(p1, p2);

		// checks if the line has a distance of a minimum of 5 pixels so the
		// player can't click the fruit
		if (p1.distance(p2) < 5) {
			return false;
		}
		/*
		 * make sure the gameobject exists, if so we create a rectangle around
		 * the fruit and check if the line intersects it
		 */
		if (playingField.getGameObject() == null) {
			return false;
		} else {
			Rectangle r = playingField.getGameObject().getBounds();
			if (line.intersects(r)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void setPause(boolean pauseOn) {
		if (pauseOn) {
			// used for the endpanel to stop the music
			soundplayer.stopMusic();
		} else {
			soundplayer.playMusic();
		}
	}
	
	public int getWORLDWIDTH() {
		return WORLDWIDTH;
	}

	public int getWORLDHEIGHT() {
		return WORLDHEIGHT;
	}
}
