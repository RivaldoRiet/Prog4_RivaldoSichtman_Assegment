package view;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;



 //scorelabel creates two labels and updates the lives and score by observing the player
 
@SuppressWarnings("serial")
public class ScorePane extends JPanel implements Observer {
	private JLabel scoreLabel, liveLabel;
	private int points = 0;
	private int lives = 0;

	ScorePane(Player player) {
		player.addObserver(this);
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		liveLabel = new JLabel();
		scoreLabel = new JLabel();
		liveLabel.setText("lives: 3");
		scoreLabel.setText("Points: 0");
		//set the size
        this.setPreferredSize(new Dimension(500, 35));
        //gridlayout 1 by 1
		this.setLayout(new GridLayout(1, 1));
        this.setOpaque(false);
        liveLabel.setAlignmentX(RIGHT_ALIGNMENT);
        
        this.add(scoreLabel);
        this.add(liveLabel);
	}

	@Override
	public void update(Observable o, Object player) {
		//updates the points of the player
		Player play = (Player) player;
		points = play.getScore();
		lives = play.getLives();
		liveLabel.setText("lives: " + lives + "  ");
		scoreLabel.setText("Points: " + points + "  ");
	}
}
