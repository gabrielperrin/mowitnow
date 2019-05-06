package main.java.gpe.mowitnow.input;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.gpe.mowitnow.Mower;

public class MowerPrograms {

	private static final Logger LOGGER = LogManager.getLogger(MowerPrograms.class.getName());

	private List<MowerProgram> list;

	public MowerPrograms() {
		super();
		this.list = new ArrayList<MowerProgram>();
	}

	public void addMowerProgram(MowerProgram mowerProgram) {
		list.add(mowerProgram);
	}

	public void launch() throws Exception {
		for (MowerProgram mowerProgram : list) {
			Mower mower = mowerProgram.getMower();
			if (mower == null)
				continue;
			String commands = mowerProgram.getCommands();
			LOGGER.info("Initial position for " + mower.getName() + ": " + mower.positionToString());
			mower.sendCommands(commands);
			LOGGER.info("Final position for " + mower.getName() + ": " + mower.positionToString());
		}
	}

}
