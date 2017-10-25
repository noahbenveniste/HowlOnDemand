package edu.ncsu.csc216.howlondemand.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.AudioTrackList;
import edu.ncsu.csc216.audioxml.xml.AudioTrackXML;
import edu.ncsu.csc216.audioxml.xml.StationXML;
import edu.ncsu.csc216.audioxml.xml.TrackChunkXML;

/**
 * Unit tests for Station
 * @author Noah Benveniste
 */
public class StationTest {
	/** StationXML reference to be used throughout tests */
	private StationXML sXML;
	/** AudioTrackList reference to be used throughout tests */
	private AudioTrackList listXML;
	/** Valid station id */
	private static final int STATION_ID = 1;
	/** Valid station title */
	private static final String STATION_TITLE = "My Station";
	/** Valid station color */
	private static final int STATION_COLOR = 0;
	/** Invalid station ID */
	private static final int INVALID_ID = -1;
	/** Invalid station title */
	private static final String INVALID_TITLE = null;
	/** Invalid station color */
	private static final int INVALID_COLOR_1 = -1;
	/** Invalid station color */
	private static final int INVALID_COLOR_2 = 6;
	
	/** First test chunk */
	private static final String S1 = "01A2FDB9";
	/** Second test chunk */
	private static final String S2 = "98FBCA01";
	/** Third test chunk */
	private static final String S3 = "AB4AD012";
	/** Fourth test chunk */
	private static final String S4 = "DB56AFE9";
	/** Fifth test chunk */
	private static final String S5 = "7EA2FB3D";
	/** Sixth test chunk */
	private static final String S6 = "9FA4DC3B";
	/** Invalid track chunk */
	private static final String INVALID_CHUNK = "XXXXXXXX";
	
	/**
	 * Initializes XML track chunks, audio tracks, audio track list, and station to be used in tests
	 */
	@Before
	public void setUp() {
		//Initialize track list to be stored by StationXML
		listXML = new AudioTrackList();
		
		//Create a chunk list object for the first track, add chunks
		TrackChunkXML chunk1 = new TrackChunkXML();
		chunk1.getChunk().add(S1);
		chunk1.getChunk().add(S3);
		chunk1.getChunk().add(S5);
		
		assertEquals(3, chunk1.getChunk().size());
		
		//Add the chunk list to the first track
		AudioTrackXML track1 = new AudioTrackXML();
		track1.setTrackChunks(chunk1);
		
		//Create a chunk list for the second track, add chunks
		TrackChunkXML chunk2 = new TrackChunkXML();
		chunk2.getChunk().add(S2);
		chunk2.getChunk().add(S4);
		chunk2.getChunk().add(S6);
		
		assertEquals(3, chunk2.getChunk().size());
		
		//Add the chunk list to the second track
		AudioTrackXML track2 = new AudioTrackXML();
		track2.setTrackChunks(chunk2);
		
		//Add the two tracks to the track list
		listXML.getAudioTrackXML().add(track1);
		listXML.getAudioTrackXML().add(track2);
		
		assertEquals(2, listXML.getAudioTrackXML().size());
		
		//Set the station's parameters, including the track list
		sXML = new StationXML();
		sXML.setId(STATION_ID);
		sXML.setTitle(STATION_TITLE);
		sXML.setColor(STATION_COLOR);
		sXML.setAudioTracks(listXML);
		
		assertFalse(sXML.isRepeat());
		assertFalse(sXML.isShuffle());
	}
	
	/**
	 * Test for from-scratch constructor
	 */
	@Test
	public void testStationIntStringInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test for constructor that is passed a StationXML object
	 */
	@Test
	public void testStationStationXML() {
		fail("Not yet implemented");
	}

	/**
	 * Test for getId()
	 */
	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	/**
	 * Test for setId()
	 */
	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	/**
	 * Test for getTitle()
	 */
	@Test
	public void testGetTitle() {
		fail("Not yet implemented");
	}

	/**
	 * Test for setTitle()
	 */
	@Test
	public void testSetTitle() {
		fail("Not yet implemented");
	}

	/**
	 * Test for getRepeat()
	 */
	@Test
	public void testGetRepeat() {
		fail("Not yet implemented");
	}

	/**
	 * Test for toggleRepeat()
	 */
	@Test
	public void testToggleRepeat() {
		fail("Not yet implemented");
	}

	/**
	 * Test for getShuffle()
	 */
	@Test
	public void testGetShuffle() {
		fail("Not yet implemented");
	}

	/**
	 * 
	 */
	@Test
	public void testToggleShuffle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlaylist() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAudioTrack() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasNextTrack() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentAudioTrack() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetColor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testReset() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
