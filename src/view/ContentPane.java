package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controller.MainController;
import model.Player;
import model.PlayingField;
// the contentpane that contains score panel and the playing panel
@SuppressWarnings("serial")
public class ContentPane extends JPanel{
    private ScorePane scorePane;
    private PlayingPane playingPane;
	public ContentPane(MainController mainController, PlayingField playingField, Player player){
		this.setLayout(new BorderLayout());
		scorePane = new ScorePane(player);
		playingPane = new PlayingPane(mainController, playingField);
		this.add(scorePane, BorderLayout.NORTH);
		this.add(playingPane, BorderLayout.CENTER);
	}

}
