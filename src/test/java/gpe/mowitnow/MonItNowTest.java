package test.java.gpe.mowitnow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.gpe.mowitnow.Lawn;
import main.java.gpe.mowitnow.Mower;
import main.java.gpe.mowitnow.utils.CardinalDirection;

class MonItNowTest {
	private static final Logger LOGGER = LogManager.getLogger(MonItNowTest.class.getName());

	private String mower1Name;
	private String mower2Name;
	private String mower1Commands;
	private String mower2Commands;
	private int lawnMaxX;
	private int lawnMaxY;
	private int mower1InitialX;
	private int mower1InitialY;
	private char mower1InitialDirection;
	private int mower2InitialX;
	private int mower2InitialY;
	private char mower2InitialDirection;

	@BeforeAll
	public static void oneTimeSetUp() {
		LOGGER.traceEntry();
		LOGGER.traceExit();
	}

	@AfterAll
	public static void oneTimeTearDown() {
	}

	@BeforeEach
	public void setUp() {
		LOGGER.traceEntry();

		lawnMaxX = 5;
		lawnMaxY = 5;

		mower1Name = "Mower-1";
		mower1InitialX = 1;
		mower1InitialY = 2;
		mower1InitialDirection = 'N';

		mower2Name = "Mower-2";
		mower2InitialX = 3;
		mower2InitialY = 3;
		mower2InitialDirection = 'E';

		mower1Commands = "GAGAGAGAA";
		mower2Commands = "AADAADADDA";

		LOGGER.traceExit();
	}

	@AfterEach
	public void tearDown() {
	}

	@Test
	void testMower() throws Exception {
		LOGGER.traceEntry();

		Lawn lawn = new Lawn(lawnMaxX, lawnMaxY);
		assertNotNull(lawn);
		assertEquals(lawnMaxX, lawn.getMaxX());
		assertEquals(lawnMaxY, lawn.getMaxY());

		Mower mower1 = new Mower(mower1InitialX, mower1InitialY, lawn, mower1InitialDirection, mower1Name);
		checkCommandResults(mower1, mower1Commands, mower1InitialX, mower1InitialY,
				CardinalDirection.getCardinalDirectionForCharCode(mower1InitialDirection), 1, 3,
				CardinalDirection.NORTH);

		Mower mower2 = new Mower(mower2InitialX, mower2InitialY, lawn, mower2InitialDirection, mower2Name);
		checkCommandResults(mower2, mower2Commands, mower2InitialX, mower2InitialY,
				CardinalDirection.getCardinalDirectionForCharCode(mower2InitialDirection), 5, 1,
				CardinalDirection.EAST);

		LOGGER.traceExit();
	}

	private void checkCommandResults(Mower mower, String mowerCommands, int mowerInitialX, int mowerInitialY,
			CardinalDirection mowerInitialDirection, int mowerFinalX, int mowerFinalY,
			CardinalDirection mowerFinalDirection) throws Exception {
		assertNotNull(mower);

		assertEquals(mowerInitialX, mower.getX());
		assertEquals(mowerInitialY, mower.getY());
		assertEquals(mowerInitialDirection, mower.getCardinalDirection());

		LOGGER.info("Initial position for " + mower.getName() + ": " + mower.positionToString());

		mower.sendCommands(mowerCommands);

		assertEquals(mowerFinalX, mower.getX());
		assertEquals(mowerFinalY, mower.getY());
		assertEquals(mowerFinalDirection, mower.getCardinalDirection());
		LOGGER.info("Final position for " + mower.getName() + ": " + mower.positionToString());
	}

}
