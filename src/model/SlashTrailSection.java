package model;

public class SlashTrailSection {
	private int startX, startY, endX, endY;

	public void updateSlice(int endX, int endY) {
		//updates the end point of the line
		setEndX(endX);
		setEndY(endY);
	}

	
	//getters and setters
	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

}
