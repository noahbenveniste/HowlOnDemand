package edu.ncsu.csc216.howlondemand.platform;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationIOException;
import edu.ncsu.csc216.howlondemand.model.TrackChunk;
import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

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
	
	/** Reference to the HowlOnDemandSystem to be used throughout testing */
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
		h = HowlOnDemandSystem.getInstance();
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
		} catch (IllegalArgumentException e) {
			
		} catch (StationIOException e) {
			fail();
		} catch ( MalformedTrackException e) {
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
		//Load the station data
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException | StationIOException e) {
			fail();
		}
		
		//Set the first station in the station collection as the current station
		h.loadStation(h.getStations().get(0));
		
		assertEquals(0, h.getCurrentStation().getId());
		assertEquals(0, h.getCurrentStation().getIndex());
		assertEquals(4, h.getCurrentStation().getColor());
		assertEquals("Rainy Day", h.getCurrentStation().getTitle());
		assertFalse(h.getCurrentStation().getShuffle());
		assertFalse(h.getCurrentStation().getRepeat());
	}

	/**
	 * Test method for getCurrentAudioTrack()
	 */
	@Test
	public void testGetCurrentAudioTrack() {
		//Load the station data
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException | StationIOException e) {
			fail();
		}
		
		//Set the first station in the station collection as the current station
		h.loadStation(h.getStations().get(0));
		assertEquals(h.getStations().get(0), h.getCurrentStation());
		
		//Get the current track from the station
		assertEquals(0, h.getCurrentAudioTrack().getId());
		assertEquals("Hozier", h.getCurrentAudioTrack().getArtist());
		assertEquals("Work Song", h.getCurrentAudioTrack().getTitle());
		assertEquals(0, h.getCurrentAudioTrack().getChunkIndex());
		
		//Try reading in chunks from the track
		assertEquals("619D90A1", h.getCurrentAudioTrack().getNextChunk().toString());
		assertEquals(1, h.getCurrentAudioTrack().getChunkIndex());
		
		assertEquals("FFA094E6", h.getCurrentAudioTrack().getNextChunk().toString());
		assertEquals(2, h.getCurrentAudioTrack().getChunkIndex());
	}

	/**
	 * Test method for reset()
	 */
	@Test
	public void testReset() {
		//Load the station data
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException | StationIOException e) {
			fail();
		}
		assertEquals(9, h.getStations().size());
		
		//Set the first station in the station collection as the current station
		h.loadStation(h.getStations().get(0));
		assertEquals(h.getStations().get(0), h.getCurrentStation());
		
		//Reset the system
		h.reset();
		assertNull(h.getCurrentStation());
		assertEquals(0, h.getStations().size());
		assertEquals(0, h.getChunkSize());
		assertEquals("Selection", h.getState().getStateName());
	}

	/**
	 * Test method for toString()
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for getChunkSize()
	 */
	@Test
	public void testGetChunkSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for consumeTrackChunk()
	 */
	@Test
	public void testConsumeTrackChunk() {
		//Load the station data
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException | StationIOException e) {
			fail();
		}
		
		//Set the first station in the station collection as the current station
		h.loadStation(h.getStations().get(0));
		
		while (h.getCurrentAudioTrack().hasNextChunk()) {
			h.addTrackChunkToBuffer(h.getCurrentAudioTrack().getNextChunk());
		}
		
		assertEquals("619D90A1", h.consumeTrackChunk().toString());
		assertEquals("FFA094E6", h.consumeTrackChunk().toString());
		assertEquals("AA30E704", h.consumeTrackChunk().toString());
		assertEquals("242FC1D9", h.consumeTrackChunk().toString());
		assertEquals("DC77D816", h.consumeTrackChunk().toString());
		assertEquals("DCC30F29", h.consumeTrackChunk().toString());
		assertEquals("28861925", h.consumeTrackChunk().toString());
		assertEquals("305286FD", h.consumeTrackChunk().toString());
		assertEquals("99BF4EA4", h.consumeTrackChunk().toString());
		assertEquals("26B8B386", h.consumeTrackChunk().toString());
		assertEquals("72899338", h.consumeTrackChunk().toString());
		assertEquals("BA2D5D64", h.consumeTrackChunk().toString());
		assertEquals("F5FD578A", h.consumeTrackChunk().toString());
		assertEquals("71867FA4", h.consumeTrackChunk().toString());
		assertEquals("C325176F", h.consumeTrackChunk().toString());
		assertEquals("611B257D", h.consumeTrackChunk().toString());
		assertEquals("62C7593E", h.consumeTrackChunk().toString());
		assertEquals("48AD40C0", h.consumeTrackChunk().toString());
		assertEquals("6C6290BE", h.consumeTrackChunk().toString());
		assertEquals("5E06A1CB", h.consumeTrackChunk().toString());
		assertEquals("5A46B967", h.consumeTrackChunk().toString());
		assertEquals("0CB96C41", h.consumeTrackChunk().toString());
		assertEquals("4C1419F0", h.consumeTrackChunk().toString());
		assertEquals("3A2469E5", h.consumeTrackChunk().toString());
		assertEquals("2D42C8A9", h.consumeTrackChunk().toString());
		assertEquals("21A92EA5", h.consumeTrackChunk().toString());
		assertEquals("62CA954A", h.consumeTrackChunk().toString());
		assertEquals("FD143250", h.consumeTrackChunk().toString());
		assertEquals("983E5EFD", h.consumeTrackChunk().toString());
		assertEquals("7E2CF99D", h.consumeTrackChunk().toString());
		
		assertFalse(h.hasNextTrackChunk());
	}

	/**
	 * Test method for hasNextTrackChunk()
	 */
	@Test
	public void testHasNextTrackChunk() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for addTrackChunkToBuffer()
	 */
	@Test
	public void testAddTrackChunkToBuffer() {
		//Load the station data
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException | StationIOException e) {
			fail();
		}
		
		//Set the first station in the station collection as the current station
		h.loadStation(h.getStations().get(0));
		
		while (h.getCurrentAudioTrack().hasNextChunk()) {
			h.addTrackChunkToBuffer(h.getCurrentAudioTrack().getNextChunk());
		}
		assertEquals(30, h.getCurrentAudioTrack().getChunkIndex());
		assertFalse(h.getCurrentAudioTrack().hasNextChunk());
		assertEquals(30, h.getChunkSize());
	}

	/**
	 * Test method for bufferHasRoom()
	 */
	@Test
	public void testBufferHasRoom() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for getState()
	 */
	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for selection state
	 */
	@Test
	public void testSelectionState() {
		//Load the station data
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException | StationIOException e) {
			fail();
		}
		
		Command cSelectStation = new Command(CommandValue.SELECT_STATION);
		Command cBuffering = new Command(CommandValue.BUFFERING);
		Command cNotBuffering = new Command(CommandValue.NOT_BUFFERING);
		Command cStop = new Command(CommandValue.STOP);
		Command cPlay = new Command(CommandValue.PLAY);
		Command cFinishTrack = new Command(CommandValue.FINISH_TRACK);
		Command cFinishStation = new Command(CommandValue.FINISH_STATION);
		Command cReturn = new Command(CommandValue.RETURN);
		Command cSkipForward = new Command(CommandValue.SKIP_FORWARD);
		Command cSkipBackward = new Command(CommandValue.SKIP_BACKWARD);
		
		//Set the first station in the station collection as the current station
		h.loadStation(h.getStations().get(0));
		
		/** Testing the Selection state */
		
		assertEquals("Selection", h.getState().getStateName());
		
		//Test all invalid commands
		try {
			h.executeCommand(cSelectStation);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("SELECT_STATION is not a valid command for Selection", e.getMessage());
			assertEquals("Selection", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cBuffering);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Selection", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cNotBuffering);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Selection", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cStop);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Selection", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cFinishTrack);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Selection", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cFinishStation);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Selection", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cReturn);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Selection", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cSkipForward);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Selection", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cSkipBackward);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Selection", h.getState().getStateName());
		}
		
		//Using the command PLAY should add one chunk to the buffer and set the state to PlayWithBufferingState
		h.executeCommand(cPlay);
		assertEquals(0, h.getCurrentAudioTrack().getId());
		assertEquals("Hozier", h.getCurrentAudioTrack().getArtist());
		assertEquals("Work Song", h.getCurrentAudioTrack().getTitle());
		assertEquals(1, h.getChunkSize());
		assertEquals("619D90A1", h.consumeTrackChunk().toString());
	}

	/**
	 * Test method for play with buffering state
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testPlayWithBufferingState() {
		//Load the station data
		try {
			h.loadStationsFromFile(VALID_FILE);
		} catch (MalformedTrackException | StationIOException e) {
			fail();
		}
		
		Command cSelectStation = new Command(CommandValue.SELECT_STATION);
		Command cBuffering = new Command(CommandValue.BUFFERING);
		Command cNotBuffering = new Command(CommandValue.NOT_BUFFERING);
		Command cStop = new Command(CommandValue.STOP);
		Command cPlay = new Command(CommandValue.PLAY);
		Command cFinishTrack = new Command(CommandValue.FINISH_TRACK);
		Command cFinishStation = new Command(CommandValue.FINISH_STATION);
		Command cReturn = new Command(CommandValue.RETURN);
		Command cSkipForward = new Command(CommandValue.SKIP_FORWARD);
		Command cSkipBackward = new Command(CommandValue.SKIP_BACKWARD);
		
		//Set the first station in the station collection as the current station
		h.loadStation(h.getStations().get(0));
		
		//Using the command PLAY should add one chunk to the buffer and set the state to PlayWithBufferingState
		h.executeCommand(cPlay);
		
		//Test invalid commands
		try {
			h.executeCommand(cSelectStation);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Playing with Buffering", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cPlay);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Playing with Buffering", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cFinishTrack);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Playing with Buffering", h.getState().getStateName());
		}
		
		try {
			h.executeCommand(cFinishStation);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals("Playing with Buffering", h.getState().getStateName());
		}
		
		/** Testing valid commands */
		
		//This code is used to make the chunk buffer field visible for testing
		Field chunks = null;
		try {
			chunks = HowlOnDemandSystem.class.getDeclaredField("chunks");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		chunks.setAccessible(true);
		Queue<TrackChunk> buffer = null;
		try {
			buffer = (Queue<TrackChunk>) chunks.get(h);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//The buffer should currently have chunk 0 in it. Calling BUFFERING should load the next chunk and then consume the first
		h.executeCommand(cBuffering);
		assertEquals(1, h.getChunkSize());
		assertEquals("FFA094E6", buffer.peek().toString());
		assertEquals("Playing with Buffering", h.getState().getStateName());
	}
	
	/**
	 * Test method for play without buffering state
	 */
	@Test
	public void testPlayWithoutBufferingState() {
		
	}
	
	/**
	 * Test method for stop with buffering state
	 */
	@Test
	public void testStopWithBufferingState() {
		
	}
	
	/**
	 * Test method for stop without buffering state
	 */
	@Test
	public void testStopWithoutBufferingState() {
		
	}
	
	/**
	 * Test method for quit state
	 */
	@Test
	public void testQuitState() {
		
	}
	
	/**
	 * Test method for finished state
	 */
	@Test
	public void testFinishedState() {
		
	}
	
}
