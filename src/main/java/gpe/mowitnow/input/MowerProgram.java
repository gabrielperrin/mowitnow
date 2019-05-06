package main.java.gpe.mowitnow.input;

import main.java.gpe.mowitnow.Lawn;
import main.java.gpe.mowitnow.Mower;

public class MowerProgram {

	private Lawn lawn;
	private Mower mower;
	private String commands;

	public MowerProgram(Lawn lawn, Mower mower, String commands) {
		super();
		this.lawn = lawn;
		this.mower = mower;
		this.commands = commands;
	}

	public Lawn getLawn() {
		return lawn;
	}

	public Mower getMower() {
		return mower;
	}

	public String getCommands() {
		return commands;
	}

}
