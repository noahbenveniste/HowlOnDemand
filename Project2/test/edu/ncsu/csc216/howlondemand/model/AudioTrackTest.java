package edu.ncsu.csc216.howlondemand.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.AudioTrackXML;
import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.TrackChunkXML;

/**
 * Unit tests for AudioTrack its inherited methods from Multimedia
 * @author Noah Benveniste
 */
public class AudioTrackTest {
	/** The test AudioTrack reference to use */
	private AudioTrack t;
	/** The test TrackChunkXML to use */
	private TrackChunkXML cXML;
	/** The test AudioTrackXML to use */
	private AudioTrackXML tXML;
	/** Test track ID */
	private static final int TRACK_ID = 1;
	/** Test track artist */
	private static final String TRACK_ARTIST = "Periphery";
	/** Test track title */
	private static final String TRACK_TITLE = "The Gods Must Be Crazy!"; 
	/** First test chunk */
	private static final String S1 = "00000000";
	/** Second test chunk */
	private static final String S2 = "00000001";
	/** Third test chunk */
	private static final String S3 = "00000002";
	/** Fourth test chunk */
	private static final String S4 = "00000003";
	/** Fifth test chunk */
	private static final String S5 = "00000004";
	/** Invalid track chunk */
	private static final String INVALID_CHUNK = "XXXXXXXX";
	/** Invalid track ID */
	private static final int INVALID_ID = -1;
	/** Invalid track title */
	private static final String INVALID_TITLE = null;
	/** Invalid track artist */
	private static final String INVALID_ARTIST = null;
	/** Bad ID error message */
	private static final String ID_ERROR = "Track ID is invalid, cannot be negative";
	/** Bad title error message */
	private static final String TITLE_ERROR = "Track title cannot be null";
	/** Bad artist error message */
	private static final String ARTIST_ERROR = "Artist cannot be null";
	/** Bad chunk error message */
	private static final String CHUNK_ERROR = "Invalid track chunk, either contains invalid hexadecimal digits or is not 8 characters long";
	/** Bad chunk index error message */
	private static final String INDEX_ERROR = "Chunk index cannot be less than zero or greater than the size of the chunk collection";
	/** Empty chunk collection error message */
	private static final String NO_MORE_CHUNKS = "No chunks left in track's chunk collection";
	/** Track string format */
	private static final String TRACK_STRING = "1,The Gods Must Be Crazy!,Periphery";
	
	/**
	 * Initializes each test with a AudioTrackXML object to use
	 */
	@Before
	public void setUp() {
		cXML = new TrackChunkXML();
		cXML.getChunk().add(S1);
		cXML.getChunk().add(S2);
		cXML.getChunk().add(S3);
		cXML.getChunk().add(S4);
		cXML.getChunk().add(S5);
		
		tXML = new AudioTrackXML();
		tXML.setId(TRACK_ID);
		tXML.setArtist(TRACK_ARTIST);
		tXML.setTitle(TRACK_TITLE);
		tXML.setTrackChunks(cXML);
		
		t = null;
	}

	/**
	 * Test method for AudioTrack(int, String, String)
	 */
	@Test
	public void testAudioTrackIntStringString() {
		//Try creating an AudioTrack from scratch; should have an empty track chunk collection
		try {
			t = new AudioTrack(TRACK_ID, TRACK_TITLE, TRACK_ARTIST);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(0, t.getTrackChunkSize());
	}

	/**
	 * Test method for AudioTrack(AudioTrackXML)
	 */
	@Test
	public void testAudioTrackAudioTrackXML() {
		//Try creating with an AudioTrackXML that has an invalid id
		tXML.setId(INVALID_ID);
		try {
			t = new AudioTrack(tXML);
			fail();
		} catch (MalformedTrackException e) {
			assertNull(t);
			assertEquals(ID_ERROR, e.getMessage());
		}
		//Set the ID to a valid one
		tXML.setId(TRACK_ID);
		
		//Invalid title
		tXML.setTitle(INVALID_TITLE);
		try {
			t = new AudioTrack(tXML);
			fail();
		} catch (MalformedTrackException e) {
			assertNull(t);
			assertEquals(TITLE_ERROR, e.getMessage());
		}
		//Set the title to a valid one
		tXML.setTitle(TRACK_TITLE);
		
		//Invalid artist
		tXML.setArtist(INVALID_ARTIST);
		try {
			t = new AudioTrack(tXML);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(t);
			assertEquals(ARTIST_ERROR, e.getMessage());
		} catch (MalformedTrackException e) {
			fail("Unexpected MalformedTrackException");
		}
		//Set the artist to a valid one
		tXML.setArtist(TRACK_ARTIST);
		
		//Bad chunk data
		tXML.getTrackChunks().getChunk().add(INVALID_CHUNK);
		try {
			t = new AudioTrack(tXML);
			fail();
		} catch (MalformedTrackException e) {
			assertNull(t);
			assertEquals(CHUNK_ERROR, e.getMessage());
		}
		//Remove the invalid chunk
		tXML.getTrackChunks().getChunk().remove(cXML.getChunk().size() - 1);
		
		//Valid construction
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail();
		}
		assertEquals(TRACK_ID, t.getId());
		assertEquals(TRACK_TITLE, t.getTitle());
		assertEquals(TRACK_ARTIST, t.getArtist());
		assertEquals(0, t.getChunkIndex());
	}

	/**
	 * Test method for getTrackChunkSize()
	 */
	@Test
	public void testGetTrackChunkSize() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(5, t.getTrackChunkSize());
		
		//Try adding an element and checking that the size increases
		try {
			t.addChunk(new TrackChunk(S2));
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(6, t.getTrackChunkSize());
	}

	/**
	 * Test method for getId()
	 */
	@Test
	public void testGetId() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(TRACK_ID, t.getId());
	}

	/**
	 * Test method for setId()
	 */
	@Test
	public void testSetId() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		
		//Set invalid ID
		try {
			t.setId(-1);
			fail();
		} catch (MalformedTrackException e) {
			assertEquals(ID_ERROR, e.getMessage());
			assertEquals(TRACK_ID, t.getId());
		}
		
		//Set a valid ID
		try {
			t.setId(2);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(2, t.getId());
	}

	/**
	 * Test method for getTitle()
	 */
	@Test
	public void testGetTitle() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(TRACK_TITLE, t.getTitle());
	}

	/**
	 * Test method for setTitle()
	 */
	@Test
	public void testSetTitle() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(TRACK_TITLE, t.getTitle());
		
		//Try setting an invalid title
		try {
			t.setTitle(INVALID_TITLE);
			fail();
		} catch (MalformedTrackException e) {
			assertEquals(TRACK_TITLE, t.getTitle());
			assertEquals(TITLE_ERROR, e.getMessage());
		}
		
		//Set a valid title, check that it updates
		try {
			t.setTitle("New Title");
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals("New Title", t.getTitle());
	}

	/**
	 * Test method for getArtist()
	 */
	@Test
	public void testGetArtist() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(TRACK_ARTIST, t.getArtist());
	}

	/**
	 * Test method for setArtist()
	 */
	@Test
	public void testSetArtist() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(TRACK_ARTIST, t.getArtist());
		
		//Try setting an invalid Artist
		try {
			t.setArtist(INVALID_ARTIST);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(TRACK_ARTIST, t.getArtist());
			assertEquals(ARTIST_ERROR, e.getMessage());
		}
		
		//Set a valid Artist, check that it updates
		try {
			t.setArtist("New Artist");
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		assertEquals("New Artist", t.getArtist());
	}

	/**
	 * Test method for getChunkIndex()
	 */
	@Test
	public void testGetChunkIndex() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(0, t.getChunkIndex());
	}

	/**
	 * Test method for setChunkIndex()
	 */
	@Test
	public void testSetChunkIndex() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		
		//Try setting an index less than zero
		try { 
			t.setChunkIndex(-1);
		} catch (IllegalArgumentException e) {
			assertEquals(0, t.getChunkIndex());
			assertEquals(INDEX_ERROR, e.getMessage());
		}
		
		//Try setting an index greater than the collection size
		try {
			t.setChunkIndex(6);
		} catch (IllegalArgumentException e) {
			assertEquals(0, t.getChunkIndex());
			assertEquals(INDEX_ERROR, e.getMessage());
		}
		
		//Try setting at a valid index, verify that the next chunk is correct
		try {
			t.setChunkIndex(2);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		assertEquals(S3, t.getNextChunk().getChunk());
		
		//Grow the chunk collection
		try {
			t.addChunk(new TrackChunk(S2));
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(6, t.getTrackChunkSize());
		
		//Try setting at an index greater than the new collection size
		try {
			t.setChunkIndex(7);
		} catch (IllegalArgumentException e) {
			assertEquals(3, t.getChunkIndex());
			assertEquals(INDEX_ERROR, e.getMessage());
		}
		
		//Set at the new collection size
		try {
			t.setChunkIndex(5);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		assertEquals(S2, t.getNextChunk().getChunk());
		assertEquals(6, t.getChunkIndex());
		assertFalse(t.hasNextChunk());
	}

	/**
	 * Test method for addChunk()
	 */
	@Test
	public void testAddChunk() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(0, t.getChunkIndex());
		assertEquals(S1, t.getNextChunk().getChunk());
		assertEquals(1, t.getChunkIndex());
		assertEquals(S2, t.getNextChunk().getChunk());
		assertEquals(2, t.getChunkIndex());
		assertEquals(S3, t.getNextChunk().getChunk());
		assertEquals(3, t.getChunkIndex());
		assertEquals(S4, t.getNextChunk().getChunk());
		assertEquals(4, t.getChunkIndex());
		assertEquals(S5, t.getNextChunk().getChunk());
		assertEquals(5, t.getChunkIndex());
		assertFalse(t.hasNextChunk());
		
		//Reset the chunk index
		try {
			t.setChunkIndex(0);
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
		
		//Grow the collection
		try {
			t.addChunk(new TrackChunk(S2));
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(6, t.getTrackChunkSize());
		
		assertEquals(0, t.getChunkIndex());
		assertEquals(S1, t.getNextChunk().getChunk());
		assertEquals(1, t.getChunkIndex());
		assertEquals(S2, t.getNextChunk().getChunk());
		assertEquals(2, t.getChunkIndex());
		assertEquals(S3, t.getNextChunk().getChunk());
		assertEquals(3, t.getChunkIndex());
		assertEquals(S4, t.getNextChunk().getChunk());
		assertEquals(4, t.getChunkIndex());
		assertEquals(S5, t.getNextChunk().getChunk());
		assertEquals(5, t.getChunkIndex());
		
		//Check that the new chunk is recognized properly
		assertTrue(t.hasNextChunk());
		
		assertEquals(S2, t.getNextChunk().getChunk());
		assertEquals(6, t.getChunkIndex());
		assertFalse(t.hasNextChunk());
	}

	/**
	 * Test method for hasNextChunk()
	 */
	@Test
	public void testHasNextChunk() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(0, t.getChunkIndex());
		assertEquals(S1, t.getNextChunk().getChunk());
		assertTrue(t.hasNextChunk());
		assertEquals(1, t.getChunkIndex());
		assertEquals(S2, t.getNextChunk().getChunk());
		assertTrue(t.hasNextChunk());
		assertEquals(2, t.getChunkIndex());
		assertEquals(S3, t.getNextChunk().getChunk());
		assertTrue(t.hasNextChunk());
		assertEquals(3, t.getChunkIndex());
		assertEquals(S4, t.getNextChunk().getChunk());
		assertTrue(t.hasNextChunk());
		assertEquals(4, t.getChunkIndex());
		assertEquals(S5, t.getNextChunk().getChunk());
		assertEquals(5, t.getChunkIndex());
		assertFalse(t.hasNextChunk());
	}

	/**
	 * Test method for getNextChunk()
	 */
	@Test
	public void testGetNextChunk() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(0, t.getChunkIndex());
		assertEquals(S1, t.getNextChunk().getChunk());
		assertEquals(1, t.getChunkIndex());
		assertEquals(S2, t.getNextChunk().getChunk());
		assertEquals(2, t.getChunkIndex());
		assertEquals(S3, t.getNextChunk().getChunk());
		assertEquals(3, t.getChunkIndex());
		assertEquals(S4, t.getNextChunk().getChunk());
		assertEquals(4, t.getChunkIndex());
		assertEquals(S5, t.getNextChunk().getChunk());
		assertEquals(5, t.getChunkIndex());
		try {
			t.getNextChunk();
		} catch (IllegalArgumentException e) {
			assertEquals(5, t.getChunkIndex());
			assertEquals(NO_MORE_CHUNKS, e.getMessage());
		}
	}

	/**
	 * Test method for toString()
	 */
	@Test
	public void testToString() {
		try {
			t = new AudioTrack(tXML);
		} catch (MalformedTrackException e) {
			fail(e.getMessage());
		}
		assertEquals(TRACK_STRING, t.toString());
	}
}
