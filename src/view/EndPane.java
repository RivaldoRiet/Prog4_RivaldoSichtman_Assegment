package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
//the end panel of the game
@SuppressWarnings("serial")
public class EndPane extends JPanel {
	private JLabel jlabel;
	private JLabel scoreLabel;
	private Font font = new Font("Verdana", Font.BOLD, 40);
	private Font secondFont = new Font("Verdana", Font.BOLD, 30);

	public EndPane() {
		jlabel = new JLabel();
		scoreLabel = new JLabel();
		jlabel.setFont(font);
		scoreLabel.setFont(secondFont);
		jlabel.setText("Game Over!");
		this.add(jlabel);
		this.add(scoreLabel);
	}

	public void setScore(int score) {
		//the main controller after the game ends calls the setscore method to set the final score
		scoreLabel.setText("Your score is: " + String.valueOf(score));
	}

}
