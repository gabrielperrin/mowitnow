package main.java.gpe.mowitnow.input;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.gpe.mowitnow.Lawn;
import main.java.gpe.mowitnow.Mower;
import main.java.gpe.mowitnow.utils.CardinalDirection;

public class MowerCommandReader {

	private static final Logger LOGGER = LogManager.getLogger(MowerCommandReader.class.getName());

	private String filepath;

	public MowerCommandReader(String filepath) {
		super();
		this.filepath = filepath;
	}

	public MowerPrograms buildMowerPrograms() throws Exception {
		URL url = this.getClass().getClassLoader().getResource(filepath);
		LOGGER.info("mower program file URL: " + url);
		InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filepath);
		InputStreamReader in = new InputStreamReader(resourceAsStream);
		BufferedReader reader = new BufferedReader(in);
		String line = reader.readLine();
		Lawn lawn = buildLawnFromString(line.trim());

		MowerPrograms mowerPrograms = new MowerPrograms();
		int i = 1;
		while ((line = reader.readLine()) != null) {
			Mower mower = buildMowerFromString(line, lawn, "Mower-" + i);
			i++;
			String commands = reader.readLine();
			MowerProgram mowerProgram = new MowerProgram(lawn, mower, commands);
			mowerPrograms.addMowerProgram(mowerProgram);
		}

		return mowerPrograms;
	}

	private Mower buildMowerFromString(String line, Lawn lawn, String name) throws Exception {
		if (line == null)
			throw new Exception("Read line is null");
		String[] split = line.trim().split(" ");
		if (split.length != 3)
			throw new Exception("Read line does not contain expected number of words");
		int mowerX = Integer.parseInt(split[0]);
		int mowerY = Integer.parseInt(split[1]);
		char c = split[2].charAt(0);
		CardinalDirection cardinalDirectionCode = CardinalDirection.getCardinalDirectionForCharCode(c);
		return new Mower(mowerX, mowerY, lawn, cardinalDirectionCode, name);
	}

	private Lawn buildLawnFromString(String line) throws Exception {
		if (line == null)
			throw new Exception("Read line is null");
		String[] split = line.trim().split(" ");
		if (split.length != 2)
			throw new Exception("Read line does not contain expected number of words");
		int lawnMaxX = Integer.parseInt(split[0]);
		int lawnMaxY = Integer.parseInt(split[1]);
		return new Lawn(lawnMaxX, lawnMaxY);
	}

}
