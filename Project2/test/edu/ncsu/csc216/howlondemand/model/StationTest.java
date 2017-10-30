package edu.ncsu.csc216.howlondemand.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.AudioTrackList;
import edu.ncsu.csc216.audioxml.xml.AudioTrackXML;
import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
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
	/** Station reference to be used throughout testing */
	private Station s;
	/** AudioTrackXML reference to be used throughout testing */
	private AudioTrackXML track1;
	/** AudioTrackXML reference to be used throughout testing */
	private AudioTrackXML track2;
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
	/** Invalid track ID */
	private static final int INVALID_TRACK_ID = -1;
	/** Invalid track title */
	private static final String INVALID_TRACK_TITLE = null;
	/** Invalid track artist */
	private static final String INVALID_ARTIST = null;
	
	/** Bad station id error */
	private static final String STATION_ID_ERROR = "Invalid station ID, cannot be negative";
	/** Bad station title error */
	private static final String STATION_TITLE_ERROR = "Station title cannot be null";
	/** Bad station color error */
	private static final String STATION_COLOR_ERROR = "Invalid color value, must be between 0 and 5 inclusive";
	/** Bad ID error message */
	private static final String ID_ERROR = "Track ID is invalid, cannot be negative";
	/** Bad title error message */
	private static final String TITLE_ERROR = "Track title cannot be null";
	/** Bad artist error message */
	private static final String ARTIST_ERROR = "Artist cannot be null";
	/** Test track ID */
	private static final int TRACK_ID_1 = 1;
	/** Test track artist */
	private static final String TRACK_ARTIST_1 = "Periphery";
	/** Test track title */
	private static final String TRACK_TITLE_1 = "The Gods Must Be Crazy!";
	/** Test track ID */
	private static final int TRACK_ID_2 = 2;
	/** Test track artist */
	private static final String TRACK_ARTIST_2 = "Animals As Leaders";
	/** Test track title */
	private static final String TRACK_TITLE_2 = "Song of Solomon"; 
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
		track1 = new AudioTrackXML();
		track1.setTrackChunks(chunk1);
		track1.setArtist(TRACK_ARTIST_1);
		track1.setId(TRACK_ID_1);
		track1.setTitle(TRACK_TITLE_1);
		
		//Create a chunk list for the second track, add chunks
		TrackChunkXML chunk2 = new TrackChunkXML();
		chunk2.getChunk().add(S2);
		chunk2.getChunk().add(S4);
		chunk2.getChunk().add(S6);
		
		assertEquals(3, chunk2.getChunk().size());
		
		//Add the chunk list to the second track
		track2 = new AudioTrackXML();
		track2.setTrackChunks(chunk2);
		track2.setArtist(TRACK_ARTIST_2);
		track2.setId(TRACK_ID_2);
		track2.setTitle(TRACK_TITLE_2);
		
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
		
		s = null;
	}
	
//	/**
//	 * Test for from-scratch constructor
//	 */
//	@Test
//	public void testStationIntStringInt() {
//		System.out.println("Not yet implemented");
//	}

	/**
	 * Test for constructor that is passed a StationXML object
	 */
	@Test
	public void testStationStationXML() {
		//Try constructing a Station with an invalid id
		sXML.setId(INVALID_ID);
		try {
			s = new Station(sXML);
			fail();
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException thrown when it shouldn't have been"); //Shouldn't be thrown in this case
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals(STATION_ID_ERROR, e.getMessage());
		}
		sXML.setId(STATION_ID);
		
		//Try with an invalid title
		sXML.setTitle(INVALID_TITLE);
		try {
			s = new Station(sXML);
			fail();
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException thrown when it shouldn't have been"); //Shouldn't be thrown in this case
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals(STATION_TITLE_ERROR, e.getMessage());
		}
		sXML.setTitle(STATION_TITLE);
		
		//Try with invalid color (lower bound)
		sXML.setColor(INVALID_COLOR_1);
		try {
			s = new Station(sXML);
			fail();
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException thrown when it shouldn't have been"); //Shouldn't be thrown in this case
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals(STATION_COLOR_ERROR, e.getMessage());
		}
		sXML.setColor(STATION_COLOR);
		
		//Invalid color (upper bound)
		sXML.setColor(INVALID_COLOR_2);
		try {
			s = new Station(sXML);
			fail();
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException thrown when it shouldn't have been"); //Shouldn't be thrown in this case
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals(STATION_COLOR_ERROR, e.getMessage());
		}
		sXML.setColor(STATION_COLOR);
		
		//Try when the StationXML object contains corrupt/bad audio track data
		
		//Bad track id
		sXML.getAudioTracks().getAudioTrackXML().get(1).setId(INVALID_TRACK_ID);
		try {
			s = new Station(sXML);
			fail();
		} catch (IllegalArgumentException | MalformedTrackException e) {
			assertNull(s);
			assertEquals(ID_ERROR, e.getMessage());
		}
		sXML.getAudioTracks().getAudioTrackXML().get(1).setId(TRACK_ID_2);
		
		//Bad track title
		sXML.getAudioTracks().getAudioTrackXML().get(0).setTitle(INVALID_TRACK_TITLE);
		try {
			s = new Station(sXML);
			fail();
		} catch (IllegalArgumentException | MalformedTrackException e) {
			assertNull(s);
			assertEquals(TITLE_ERROR, e.getMessage());
		}
		sXML.getAudioTracks().getAudioTrackXML().get(0).setTitle(TRACK_TITLE_1);
		
		//Bad track artist
		sXML.getAudioTracks().getAudioTrackXML().get(1).setArtist(INVALID_ARTIST);
		try {
			s = new Station(sXML);
			fail();
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException was thrown when it shouldn't have");
		} catch (IllegalArgumentException e) {
			assertNull(s);
			assertEquals(ARTIST_ERROR, e.getMessage());
		}
		sXML.getAudioTracks().getAudioTrackXML().get(1).setArtist(TRACK_ARTIST_2);
		
		//Bad chunk data
		sXML.getAudioTracks().getAudioTrackXML().get(0).getTrackChunks().getChunk().add(INVALID_CHUNK);
		try {
			s = new Station(sXML);
			fail();
		} catch (IllegalArgumentException | MalformedTrackException e) {
			assertNull(s);
			assertEquals("Bad chunk data", e.getMessage());
		}
		sXML.getAudioTracks().getAudioTrackXML().get(0).getTrackChunks().getChunk().remove(INVALID_CHUNK);
		
		//Valid Station construction
		try {
			s = new Station(sXML);
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException was thrown when it shouldn't have");
		}
		assertEquals(STATION_TITLE, s.getTitle());
		assertEquals(STATION_ID, s.getId());
		assertEquals(STATION_COLOR, s.getColor());
		assertFalse(s.getShuffle());
		assertFalse(s.getRepeat());
	}

//	/**
//	 * Test for getId()
//	 */
//	@Test
//	public void testGetId() {
//		System.out.println("Not yet implemented");	}
//
//	/**
//	 * Test for setId()
//	 */
//	@Test
//	public void testSetId() {
//		System.out.println("Not yet implemented");
//	}
//
//	/**
//	 * Test for getTitle()
//	 */
//	@Test
//	public void testGetTitle() {
//		System.out.println("Not yet implemented");
//	}
//
//	/**
//	 * Test for setTitle()
//	 */
//	@Test
//	public void testSetTitle() {
//		System.out.println("Not yet implemented");
//	}
//
//	/**
//	 * Test for getRepeat()
//	 */
//	@Test
//	public void testGetRepeat() {
//		System.out.println("Not yet implemented");
//	}

	/**
	 * Test for toggleRepeat()
	 */
	@Test
	public void testToggleRepeat() {
		try {
			s = new Station(sXML);
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException was thrown when it shouldn't have");
		}
		assertFalse(s.getRepeat());
		s.toggleRepeat();
		assertTrue(s.getRepeat());
		s.toggleRepeat();
		assertFalse(s.getRepeat());
	}

//	/**
//	 * Test for getShuffle()
//	 */
//	@Test
//	public void testGetShuffle() {
//		System.out.println("Not yet implemented");
//	}

	/**
	 * Test for toggleShuffle()
	 */
	@Test
	public void testToggleShuffle() {
		try {
			s = new Station(sXML);
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException was thrown when it shouldn't have");
		}
		assertFalse(s.getShuffle());
		s.toggleShuffle();
		assertTrue(s.getShuffle());
		s.toggleShuffle();
		assertFalse(s.getShuffle());
	}

	/**
	 * Test for getPlaylist()
	 */
	@Test
	public void testGetPlaylist() {
		try {
			s = new Station(sXML);
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException was thrown when it shouldn't have");
		}
		
		ArrayList<AudioTrack> playlist = s.getPlaylist();
		assertEquals(2, playlist.size());
		
		assertEquals(TRACK_ID_1, playlist.get(0).getId());
		assertEquals(TRACK_ID_2, playlist.get(1).getId());
		assertEquals(TRACK_TITLE_1, playlist.get(0).getTitle());
		assertEquals(TRACK_TITLE_2, playlist.get(1).getTitle());
		assertEquals(TRACK_ARTIST_1, playlist.get(0).getArtist());
		assertEquals(TRACK_ARTIST_2, playlist.get(1).getArtist());
		assertEquals(S1, playlist.get(0).getNextChunk().toString());
		assertEquals(S2, playlist.get(1).getNextChunk().toString());
		
		s = new Station(STATION_ID, STATION_TITLE, STATION_COLOR);
		assertEquals(0, s.getPlaylist().size());
	}

	/**
	 * Test for addAudioTrack()
	 */
	@Test
	public void testAddAudioTrack() {
		s = new Station(STATION_ID, STATION_TITLE, STATION_COLOR);
		
		s.addAudioTrack(new AudioTrack(track2));
		
		s.addAudioTrack(new AudioTrack(track1));
		
		assertEquals(TRACK_TITLE_2, s.getPlaylist().get(0).getTitle());
		
		assertEquals(TRACK_TITLE_1, s.getPlaylist().get(1).getTitle());
	}

//	/**
//	 * Test for hasNextTrack()
//	 */
//	@Test
//	public void testHasNextTrack() {
//		System.out.println("Not yet implemented");
//	}

	/**
	 * Test for getCurrentAudioTrack()
	 */
	@Test
	public void testGetCurrentAudioTrack() {
		try {
			s = new Station(sXML);
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException was thrown when it shouldn't have");
		}
		assertTrue(s.hasNextTrack());
		assertEquals(TRACK_TITLE_1, s.getCurrentAudioTrack().getTitle());
		s.setIndex(1);
		assertEquals(TRACK_TITLE_2, s.getCurrentAudioTrack().getTitle());
		assertFalse(s.hasNextTrack());
	}

//	/**
//	 * Test for getColor()
//	 */
//	@Test
//	public void testGetColor() {
//		System.out.println("Not yet implemented");
//	}
//
//	/**
//	 * Test for setColor()
//	 */
//	@Test
//	public void testSetColor() {
//		System.out.println("Not yet implemented");
//	}

	/**
	 * Test for getIndex()
	 */
	@Test
	public void testGetIndex() {
		try {
			s = new Station(sXML);
		} catch (MalformedTrackException e) {
			fail("MalformedTrackException was thrown when it shouldn't have");
		}
		assertEquals(0, s.getIndex());
		s.setIndex(1);
		assertEquals(1, s.getIndex());
	}

//	/**
//	 * Test for setIndex()
//	 */
//	@Test
//	public void testSetIndex() {
//		System.out.println("Not yet implemented");
//	}
//
//	/**
//	 * Test for reset()
//	 */
//	@Test
//	public void testReset() {
//		System.out.println("Not yet implemented");
//	}

	/**
	 * Test for toString()
	 */
	@Test
	public void testToString() {
		s = new Station(STATION_ID, STATION_TITLE, STATION_COLOR);
		assertEquals("Station \"My Station\"", s.toString());
	}
}
