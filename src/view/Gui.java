package view;

import javax.swing.JFrame;

import controller.MainController;
import model.Player;
import model.PlayingField;

//creates a jframe
@SuppressWarnings("serial")
public class Gui extends JFrame {
	private ContentPane contentPane;
	private PlayingField playingField;
	private Thread playthread = new Thread(playingField);
	private EndPane endPane;

	public Gui(MainController mainController, PlayingField playingField, Player player) {
		this.playingField = playingField;
		endPane = new EndPane();
		contentPane = new ContentPane(mainController, playingField, player);
		this.setTitle("Rivaldo Sichtman - Fruit Ninja");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//create the game thread and set the game pane
		setGamePane();
		playthread = new Thread(playingField);
	}

	public void setGamePane() {
		this.setContentPane(contentPane);
	}

	public void setEndPane() {
		this.getContentPane().removeAll();
		contentPane.revalidate();
		this.setContentPane(endPane);
		this.validate();

	}

	public void create() {
		// show the frame and start the thread
		setResizable(false);
		pack();
		setVisible(true);
		playthread.start();
	}
	
	public EndPane getEndPane() {
		return endPane;
	}

}
