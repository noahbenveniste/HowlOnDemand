package edu.ncsu.csc216.howlondemand.platform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for HowlOnDemandSystem
 * @author Noah Benveniste
 */
public class HowlOnDemandSystemTest {
	/** */
	private static final String INVALID_FILE = "test-files/InvalidSimpleStation.xml";
	/** */
	private static final String VALID_FILE = "test-files/StationList_short.xml";
	
	/** */
	public static final String SELECTION_NAME = "Selection";
	/** */
	public static final String PLAYWITHBUFFERING_NAME = "Playing with Buffering";
	/** */
	public static final String PLAYWITHOUTBUFFERING_NAME = "Playing without Buffering";
	/** */
	public static final String STOPWITHBUFFERING_NAME = "Stopped with Buffering";
	/** */
	public static final String STOPWITHOUTBUFFERING_NAME = "Stopped without Buffering";
	/** */
	public static final String QUIT_NAME = "Quit";
	/** */
	public static final String FINISHED_NAME = "Finished";
	
	/** */
	private HowlOnDemandSystem h;
	
	/**
	 * 
	 */
	@Before
	public void setUp() {
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		h = HowlOnDemandSystem.getInstance();
		assertNull(h.getCurrentStation());
		assertEquals(0, h.getStations().size());
		assertEquals(SELECTION_NAME, h.getState().getStateName());
		assertFalse(h.hasNextTrackChunk());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#loadStationsFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadStationsFromFile() {
		fail();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getStations()}.
	 */
	@Test
	public void testGetStations() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#loadStation(edu.ncsu.csc216.howlondemand.model.Station)}.
	 */
	@Test
	public void testLoadStation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getCurrentStation()}.
	 */
	@Test
	public void testGetCurrentStation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getCurrentAudioTrack()}.
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
