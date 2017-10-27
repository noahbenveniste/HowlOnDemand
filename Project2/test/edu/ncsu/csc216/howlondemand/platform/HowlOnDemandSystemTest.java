package edu.ncsu.csc216.howlondemand.platform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationIOException;

/**
 * Unit tests for HowlOnDemandSystem
 * @author Noah Benveniste
 */
public class HowlOnDemandSystemTest {
	/** Path for file containing invalid data */
	private static final String INVALID_FILE = "test-files/InvalidSimpleStation.xml";
	/** Path for file containing valid data */
	private static final String VALID_FILE = "test-files/StationList_short.xml";
	
	/** The name of the SelectionState */
	public static final String SELECTION_NAME = "Selection";
	/** The name of the PlayWithBufferingState */
	public static final String PLAYWITHBUFFERING_NAME = "Playing with Buffering";
	/** The name of the PlayWithoutBufferingState */
	public static final String PLAYWITHOUTBUFFERING_NAME = "Playing without Buffering";
	/** The name of the StopWithBufferingState */
	public static final String STOPWITHBUFFERING_NAME = "Stopped with Buffering";
	/** The name of the StopWithoutBufferingState */
	public static final String STOPWITHOUTBUFFERING_NAME = "Stopped without Buffering";
	/** The name of the QuitState */
	public static final String QUIT_NAME = "Quit";
	/** The name of the FinishedState */
	public static final String FINISHED_NAME = "Finished";
	
	/** */
	private HowlOnDemandSystem h;
	
	/**
	 * Used to reset the singleton instance before each test case
	 */
	@Before
	public void setUp() {
		h = HowlOnDemandSystem.getInstance();
		h.reset();
	}

	/**
	 * Test method for getInstance()
	 */
	@Test
	public void testGetInstance() {
		assertNull(h.getCurrentStation());
		assertEquals(0, h.getStations().size());
		assertEquals(SELECTION_NAME, h.getState().getStateName());
		assertFalse(h.hasNextTrackChunk());
	}

	/**
	 * Test method for loadStationsFromFile()
	 */
	@Test
	public void testLoadStationsFromFile() {
		//Try loading a station that has tracks with bad chunk data
		try {
			h.loadStationsFromFile(INVALID_FILE);
			fail();
		} catch (MalformedTrackException e) {
			fail(); //Shouldn't be thrown
		} catch (StationIOException e) {
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, h.getStations().size());
			assertEquals("Bad chunk data", e.getMessage());
		}
		
		//Try loading a valid station
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException e) {
			fail(); //Shouldn't be thrown
		} catch (StationIOException e) {
			fail();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(9, h.getStations().size());
	}

	/**
	 * Test method for getStations()
	 */
	@Test
	public void testGetStations() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for loadStation()
	 */
	@Test
	public void testLoadStation() {
		//Load the station data
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException | StationIOException e) {
			fail();
		}
		
		//Set the first station in the station collection as the current station
		h.loadStation(h.getStations().get(0));
		assertEquals(h.getStations().get(0), h.getCurrentStation());
	}

	/**
	 * Test method for getCurrentStation()
	 */
	@Test
	public void testGetCurrentStation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for getCurrentAudioTrack()
	 */
	@Test
	public void testGetCurrentAudioTrack() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#reset()}.
	 */
	@Test
	public void testReset() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getChunkSize()}.
	 */
	@Test
	public void testGetChunkSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#consumeTrackChunk()}.
	 */
	@Test
	public void testConsumeTrackChunk() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#hasNextTrackChunk()}.
	 */
	@Test
	public void testHasNextTrackChunk() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#addTrackChunkToBuffer(edu.ncsu.csc216.howlondemand.model.TrackChunk)}.
	 */
	@Test
	public void testAddTrackChunkToBuffer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#bufferHasRoom()}.
	 */
	@Test
	public void testBufferHasRoom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getState()}.
	 */
	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#executeCommand(edu.ncsu.csc216.howlondemand.platform.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		fail("Not yet implemented");
	}

}
