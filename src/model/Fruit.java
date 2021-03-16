package model;

public class Fruit extends GameObject {

	private int score;

	public Fruit(int worldwidth, int worldheight, String direction, String fruitName, int size) {
		//add the parameters to the main object and gives itself the size given by the extra parameter
		super(worldwidth, worldheight, direction, fruitName);
		this.size = size;
	}
	
	@Override
	public int getScore() {
		//checks the name of the fruit and assigns points based off that
		if (getFruitName().equals("orange")) {
			score += 50;
		}
		if (getFruitName().equals("apple")) {
			score += 50;
		}
		if (getFruitName().equals("strawberry")) {
			score += 100;
		}
		return score;
	}

}