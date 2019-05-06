package main.java.gpe.mowitnow;

import main.java.gpe.mowitnow.utils.OrientedPosition;

public class Lawn {
	private int maxX;
	private int maxY;

	public Lawn(int lawnMaxX, int lawnMaxY) throws Exception {
		super();
		if (lawnMaxX < 0 || lawnMaxY < 0)
			throw new Exception("Negative lawn size - (lawnMaxX=" + lawnMaxX + ", lawnMaxY=" + lawnMaxY + ")");
		this.maxX = lawnMaxX;
		this.maxY = lawnMaxY;
	}

	public boolean contains(OrientedPosition nextPosition) {
		return nextPosition.getX() <= this.maxX && nextPosition.getY() <= this.maxY;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}
}
