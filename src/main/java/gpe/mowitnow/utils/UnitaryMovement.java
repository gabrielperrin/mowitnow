package main.java.gpe.mowitnow.utils;

public enum UnitaryMovement {
	NORTH_UNIT_MOVE(CardinalDirection.NORTH, 0, 1), EAST_UNIT_MOVE(CardinalDirection.EAST, 1, 0),
	SOUTH_UNIT_MOVE(CardinalDirection.SOUTH, 0, -1), WEST_UNIT_MOVE(CardinalDirection.WEST, -1, 0);

	private CardinalDirection cardinalDirection;
	private int incrementX = 0;
	private int incrementY = 0;

	private UnitaryMovement(CardinalDirection cardinalDirection, int incrementX, int incrementY) {
		this.cardinalDirection = cardinalDirection;
		this.incrementX = incrementX;
		this.incrementY = incrementY;
	}

	public CardinalDirection getCardinalDirection() {
		return cardinalDirection;
	}

	public int getIncrementY() {
		return incrementY;
	}

	public int getIncrementX() {
		return incrementX;
	}

}