package main.java.gpe.mowitnow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.gpe.mowitnow.input.MowerCommandReader;
import main.java.gpe.mowitnow.input.MowerPrograms;

public class Main {

	private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {
		LOGGER.traceEntry();

		try {
			if (args == null || args.length == 0)
				throw new Exception("Missing path for mower commands file in program argument.");

			String filepath = args[0];
			MowerCommandReader reader = new MowerCommandReader(filepath);
			MowerPrograms mowerPrograms = reader.buildMowerPrograms();
			mowerPrograms.launch();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.traceExit();
	}

}
