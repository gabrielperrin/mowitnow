package main.java.gpe.mowitnow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.gpe.mowitnow.utils.CardinalDirection;
import main.java.gpe.mowitnow.utils.OrientedPosition;

public class Mower {

	private static final Logger LOGGER = LogManager.getLogger(Mower.class.getName());

	private OrientedPosition mowerPosition;
	private Lawn lawn;
	private String name;

	public Mower(OrientedPosition mowerPosition, Lawn lawn, String name) throws Exception {
		super();
		this.mowerPosition = mowerPosition;
		this.lawn = lawn;
		this.name = name;
	}

	public Mower(int mowerX, int mowerY, Lawn lawn, CardinalDirection cardinalDirection, String name) throws Exception {
		this(new OrientedPosition(mowerX, mowerY, cardinalDirection), lawn, name);
	}

	public Mower(int mowerX, int mowerY, Lawn lawn, char cardinalDirectionCode, String name) throws Exception {
		this(mowerX, mowerY, lawn, CardinalDirection.getCardinalDirectionForCharCode(cardinalDirectionCode), name);
	}

	public String getName() {
		return name;
	}

	public void sendCommand(MowerCommand cmd) throws Exception {
		if (cmd == null)
			throw new Exception("Null mower command argument");
		switch (cmd) {
		case RIGHT:
			mowerPosition.pivotRight();
			LOGGER.debug("Right pivot for " + this.getName() + " - New position: " + this.mowerPosition);
			break;
		case LEFT:
			mowerPosition.pivotLeft();
			LOGGER.debug("Left pivot for " + this.getName() + " - New position: " + this.mowerPosition);
			break;
		case MV_FWD:
			moveForward();
			LOGGER.debug("Move forward for " + this.getName() + " - New position: " + this.mowerPosition);
			break;
		default:
			throw new IllegalStateException("Invalid mower command: " + cmd);
		}
	}

	private synchronized void moveForward() throws Exception {
		if (this.mowerPosition == null)
			throw new Exception("Null mower position");

		OrientedPosition nextPosition = mowerPosition.buildNextPosition();
		if (lawn.contains(nextPosition))
			this.mowerPosition.performNextUnitaryMove();
		;
	}

	public void sendCommand(char c) throws Exception {
		sendCommand(MowerCommand.getCommandForCharCode(c));
	}

	public void sendCommands(String commands) throws Exception {
		if (commands == null)
			throw new Exception("Null commands");
		char[] cmdArray = commands.trim().toUpperCase().toCharArray();
		for (char c : cmdArray) {
			sendCommand(c);
		}
	}

	public String positionToString() throws Exception {
		if (this.mowerPosition == null)
			throw new Exception("Null mower position");
		return this.mowerPosition.toString();
	}

	public CardinalDirection getCardinalDirection() throws Exception {
		if (this.mowerPosition == null)
			throw new Exception("Null mower position");
		return this.mowerPosition.getCardinalDirection();
	}

	public Integer getX() throws Exception {
		if (this.mowerPosition == null)
			throw new Exception("Null mower position");
		return this.mowerPosition.getX();
	}

	public Integer getY() throws Exception {
		if (this.mowerPosition == null)
			throw new Exception("Null mower position");
		return this.mowerPosition.getY();
	}
}
