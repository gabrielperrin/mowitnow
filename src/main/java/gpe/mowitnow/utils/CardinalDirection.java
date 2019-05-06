package main.java.gpe.mowitnow.utils;

import java.util.HashMap;
import java.util.function.Supplier;

public enum CardinalDirection {

	NORTH("NORTH", 'N'), EAST("EAST", 'E'), WEST("WEST", 'W'), SOUTH("SOUTH", 'S');

	private String name;
	private char charCode;
	private Supplier<CardinalDirection> rightPivot;
	private Supplier<CardinalDirection> leftPivot;
	private Supplier<UnitaryMovement> forwardMovement;
	private static final HashMap<Character, CardinalDirection> charCodes = new HashMap<Character, CardinalDirection>();

	CardinalDirection(String name, char charCode) {
		this.name = name;
		this.charCode = charCode;
	}

	static { // initialize pivot fields
		for (final CardinalDirection cd : CardinalDirection.values()) {
			cd.rightPivot = rightPivot(cd);
			cd.leftPivot = leftPivot(cd);
			cd.forwardMovement = forwardMovement(cd);
			charCodes.put(cd.getCharCode(), cd);
		}
	}

	private static Supplier<CardinalDirection> rightPivot(final CardinalDirection cd) {
		switch (cd) {
		case NORTH:
			return () -> EAST;
		case EAST:
			return () -> SOUTH;
		case SOUTH:
			return () -> WEST;
		case WEST:
			return () -> NORTH;
		default:
			throw new IllegalStateException("Invalid cardinal direction: " + cd);
		}
	}

	private static Supplier<CardinalDirection> leftPivot(final CardinalDirection cd) {
		switch (cd) {
		case NORTH:
			return () -> WEST;
		case EAST:
			return () -> NORTH;
		case SOUTH:
			return () -> EAST;
		case WEST:
			return () -> SOUTH;
		default:
			throw new IllegalStateException("Invalid cardinal direction: " + cd);
		}
	}

	private static Supplier<UnitaryMovement> forwardMovement(final CardinalDirection cd) {
		switch (cd) {
		case NORTH:
			return () -> UnitaryMovement.NORTH_UNIT_MOVE;
		case EAST:
			return () -> UnitaryMovement.EAST_UNIT_MOVE;
		case SOUTH:
			return () -> UnitaryMovement.SOUTH_UNIT_MOVE;
		case WEST:
			return () -> UnitaryMovement.WEST_UNIT_MOVE;
		default:
			throw new IllegalStateException("Invalid cardinal direction: " + cd);
		}
	}

	public final CardinalDirection getRightPivotDirection() {
		return rightPivot.get();
	}

	public final CardinalDirection getLeftPivotDirection() {
		return leftPivot.get();
	}

	public final UnitaryMovement getForwardMovement() {
		return forwardMovement.get();
	}

	public char getCharCode() {
		return charCode;
	}

	public static CardinalDirection getCardinalDirectionForCharCode(char c) {
		CardinalDirection cd = charCodes.get(c);
		if (cd == null)
			throw new IllegalStateException("Invalid direction character code: " + c);
		return cd;
	}
}
