package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.MainController;
import model.GameObject;
import model.PlayingField;
import model.SlashTrailSection;

@SuppressWarnings("serial")
// the panel in which the game objects move
public class PlayingPane extends JPanel implements Observer {
	private BufferedImage img;
	private GameObject gameObject;
	private MainController mainController;
	private SlashTrailSection currentSlice;
	private boolean timeIsUp = false;

	public PlayingPane(MainController mainController, PlayingField playingField) {
		this.mainController = mainController;
		playingField.addObserver(this);
		// add the mouse listeners
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		this.setPreferredSize(new Dimension(mainController.getWORLDWIDTH(), mainController.getWORLDHEIGHT()));

		// background image
		try {
			img = ImageIO.read(new File("resources/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// draws the line with the current values
		if (currentSlice != null) {
			g.setColor(Color.BLACK);
			g.drawLine(currentSlice.getStartX(), currentSlice.getStartY(), currentSlice.getEndX(),
					currentSlice.getEndY());
		}

		// draws the background image
		g2.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		// draws the fruit object
		if (gameObject != null) {
			g2.drawImage(gameObject.getImg(), gameObject.getLocationX(), gameObject.getLocationY(),
					gameObject.getSize(), gameObject.getSize(), this);
		}
	}

	private void loadImage() {

		// loads the image according to the fruit name
		if (gameObject.getFruitName() != null) {
			if (gameObject.getFruitName().equalsIgnoreCase("orange")) {
				try {
					gameObject.setImage(ImageIO.read(new File("resources/orange.png")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (gameObject.getFruitName().equalsIgnoreCase("strawberry")) {
				try {
					gameObject.setImage(ImageIO.read(new File("resources/strawberry.png")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (gameObject.getFruitName().equalsIgnoreCase("apple")) {
				try {
					gameObject.setImage(ImageIO.read(new File("resources/apple.png")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (gameObject.getFruitName().equalsIgnoreCase("bomb")) {
				try {
					gameObject.setImage(ImageIO.read(new File("resources/bomb.png")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Observable obs, Object obj) {
		/*
		 * if the object received by the observable is a game object we paint it
		 * if the object we paint is a slashtrail if the object is a boolean we
		 * know the time is up
		 */

		if (!timeIsUp) {
			if (obj instanceof GameObject) {
				// receive the game object
				gameObject = (GameObject) obj;
				loadImage();
				repaint();
			} else if (obj instanceof SlashTrailSection) {
				currentSlice = (SlashTrailSection) obj;
			} else if (obj instanceof Boolean) {
				if (((Boolean) obj).booleanValue() == true) {
					// end the game
					timeIsUp = true;
					endGame();
				}
			}
		}
	}

	public void endGame() {
		// call the maincontroller to end the game
		mainController.handleEndGame();
	}

	private MouseAdapter mouseListener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			// create a slashtrail when the mouse is pressed
			mainController.createSlice(e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			currentSlice.setEndX((int) e.getPoint().getX());
			currentSlice.setEndY((int) e.getPoint().getY());
			// checks if the line intersected with a game object when the mouse
			// is released by the user
			mainController.handleIntersection();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// as long as the user is dragging we update the end location of the
			// line
			currentSlice.updateSlice(e.getX(), e.getY());
		}
	};

}
