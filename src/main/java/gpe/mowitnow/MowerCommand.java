package main.java.gpe.mowitnow;

public enum MowerCommand {
	RIGHT(Constants.RIGHT_CMD_NAME, Constants.RIGHT_CMD_CHAR), LEFT(Constants.LEFT_CMD_NAME, Constants.LEFT_CMD_CHAR),
	MV_FWD(Constants.MOVE_FORWARD_CMD_NAME, Constants.MOVE_FORWARD_CMD_CHAR);

	private String name;
	private char charCode;

	MowerCommand(String name, char charCode) {
		this.name = name;
		this.charCode = charCode;
	}

	public static final MowerCommand getCommandForCharCode(char c) {
		for (MowerCommand cmd : MowerCommand.values()) {
			if (cmd.charCode == c)
				return cmd;
		}
		throw new IllegalStateException("Invalid command character code: " + c);
	}

	public String getName() {
		return name;
	}

	public char getCharCode() {
		return charCode;
	}

}

final class Constants {
	static final String RIGHT_CMD_NAME = "Droite";
	static final char RIGHT_CMD_CHAR = 'D';
	static final String LEFT_CMD_NAME = "Gauche";
	static final char LEFT_CMD_CHAR = 'G';
	static final String MOVE_FORWARD_CMD_NAME = "Avancer";
	static final char MOVE_FORWARD_CMD_CHAR = 'A';
}
