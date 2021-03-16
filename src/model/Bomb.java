package model;

public class Bomb extends GameObject {
	private int score;

	public Bomb(int worldwidth, int worldheight, String direction, String fruitName) {
		//add the parameters to the main object and give itself a size because it is not in the parameter
		super(worldwidth, worldheight, direction, fruitName);
		this.size = 50;
	}

	@Override
	public int getScore() {
		//the bomb never returns a score
		score = 0;
		return score;
	}

}
