package main.java.gpe.mowitnow.utils;

public class OrientedPosition {

	private int x;
	private int y;
	private CardinalDirection cardinalDirection;

	public OrientedPosition(int x, int y, CardinalDirection cardinalDirection) {
		super();
		this.x = x;
		this.y = y;
		this.cardinalDirection = cardinalDirection;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public CardinalDirection getCardinalDirection() {
		return cardinalDirection;
	}

	private void setCardinalDirection(CardinalDirection cardinalDirection) {
		this.cardinalDirection = cardinalDirection;
	}

	public void pivotRight() {
		setCardinalDirection(this.cardinalDirection.getRightPivotDirection());
	}

	public void pivotLeft() {
		setCardinalDirection(this.cardinalDirection.getLeftPivotDirection());
	}

	public OrientedPosition buildNextPosition() throws Exception {
		UnitaryMovement um = this.cardinalDirection.getForwardMovement();
		return new OrientedPosition(this.x + um.getIncrementX(), this.y + um.getIncrementY(), this.cardinalDirection);
	}

	public synchronized void performNextUnitaryMove() {
		UnitaryMovement um = this.cardinalDirection.getForwardMovement();
		this.x += um.getIncrementX();
		this.y += um.getIncrementY();
	}

	@Override
	public String toString() {
		String str = "(" + this.x + " ; " + this.y + " ; " + this.cardinalDirection.getCharCode() + ")";
		return str;
	}
}
