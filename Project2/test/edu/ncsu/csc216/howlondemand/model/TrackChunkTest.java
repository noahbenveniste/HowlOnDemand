package edu.ncsu.csc216.howlondemand.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;

/**
 * Test class for TrackChunk
 * @author Noah Benveniste
 */
public class TrackChunkTest {
	/** Expected error message for MalformedTrackException thrown by TrackChunk */
	private static final String ERROR_MSG = "Invalid track chunk, either contains invalid hexadecimal digits or is not 8 characters long";
	/** Default chunk string */
	private static final String DEFAULT = "00000000";
	/**
	 * Test method for TrackChunk() (no-arg) constructor
	 */
	@Test
	public void testTrackChunk() {
		TrackChunk chunk = null;
		try {
			chunk = new TrackChunk();
		} catch (MalformedTrackException e) {
			fail();
		}
		assertEquals(DEFAULT, chunk.getChunk());
	}

	/**
	 * Test method for validChunk()
	 */
	@Test
	public void testValidChunk() {
		String invalid = "";
		TrackChunk c = null;
		try {
			c = new TrackChunk();
		} catch (MalformedTrackException e) {
			fail();
		}
		
		//Test an invalid track chunk that is null
		invalid = null;
		assertFalse(c.validChunk(invalid));
		
		//Test an invalid track chunk that is less than 8 characters
		invalid = "1234ABC";
		assertFalse(c.validChunk(invalid));
		
		//Test an invalid  track chunk that is more than 8 characters
		invalid = "1234ABCDE";
		assertFalse(c.validChunk(invalid));
		
		//Test an invalid  track chunk that contains invalid letters
		invalid = "G1234ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1Q234ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "12Z34ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "123G4ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234XABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234ANBC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234ABMC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234ABCL";
		assertFalse(c.validChunk(invalid));
		
		//Test an invalid  track chunk that contains lowercase letters
		invalid = "1234abcd";
		assertFalse(c.validChunk(invalid));
		
		invalid = "a1234ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1b234ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "12c34ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "123d4ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234eABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234AfBC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234ABaC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234ABCb";
		assertFalse(c.validChunk(invalid));
		
		//Test an invalid track chunk that contains other invalid characters
		invalid = "!1234ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1$234ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "12#34ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "123.4ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234,ABC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234A|BC";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234AB\"C";
		assertFalse(c.validChunk(invalid));
		
		invalid = "1234ABC;";
		assertFalse(c.validChunk(invalid));
		
		//Test a valid track chunk that contains only digits
		String valid = "";
		valid = "12345678";
		assertTrue(c.validChunk(valid));
		
		valid = "99999999";
		assertTrue(c.validChunk(valid));
		
		valid = "00000000";
		assertTrue(c.validChunk(valid));
		
		//Test a valid track chunk that contains only letters
		valid = "ABCDEFAB";
		assertTrue(c.validChunk(valid));
		
		valid = "AAAAAAAA";
		assertTrue(c.validChunk(valid));
		
		valid = "FFFFFFFF";
		assertTrue(c.validChunk(valid));
		
		//Test a valid track chunk that contains letters and digits
		valid = "1A2F3BFC";
		assertTrue(c.validChunk(valid));
		
		valid = "ABCD1234";
		assertTrue(c.validChunk(valid));
		
		valid = "1234ABCD";
		assertTrue(c.validChunk(valid));
		
		valid = "A12FD45C";
		assertTrue(c.validChunk(valid));
	}

	/**
	 * Test method for TrackChunk(String) constructor
	 */
	@Test
	public void testTrackChunkString() {
		String invalid = "XXXXXXXX";
		String valid = "1234ABCD";
		TrackChunk c = null;
		
		//Try construction a TrackChunk with an invalid chunk string
		try {
			c = new TrackChunk(invalid);
			fail();
		} catch (MalformedTrackException e) {
			assertEquals(ERROR_MSG, e.getMessage());
		}
		
		//Try construction of a TrackChunk with a valid chunk string
		try {
			c = new TrackChunk(valid);
		} catch (MalformedTrackException e) {
			fail();
		}
		assertEquals(valid, c.getChunk());
	}

	/**
	 * Test method for setChunk()
	 */
	@Test
	public void testSetChunk() {
		String invalid = "XXXXXXXX";
		String valid = "1234ABCD";
		TrackChunk c = null;
		try {
			c = new TrackChunk();
		} catch (MalformedTrackException e) {
			fail();
		}
		try {
			c = new TrackChunk();
		} catch (MalformedTrackException e) {
			fail();
		}
		
		//Try setting an invalid chunk string
		try {
			c.setChunk(invalid);
			fail();
		} catch (MalformedTrackException e) {
			assertEquals(ERROR_MSG, e.getMessage());
		}
		
		//Try setting a valid chunk string
		try {
			c.setChunk(valid);
		} catch (MalformedTrackException e) {
			fail();
		}
		assertEquals(valid, c.getChunk());
	}

	/**
	 * Test method for getChunk()
	 */
	@Test
	public void testGetChunk() {
		String invalid = "XXXXXXXX";
		String valid = "1234ABCD";
		TrackChunk c = null;
		try {
			c = new TrackChunk();
		} catch (MalformedTrackException e) {
			fail();
		}
		
		try {
			c.setChunk(invalid);
			fail();
		} catch (MalformedTrackException e) {
			assertEquals(ERROR_MSG, e.getMessage());
		}
		assertEquals(DEFAULT, c.getChunk());
		
		try {
			c.setChunk(valid);
		} catch (MalformedTrackException e) {
			fail();
		}
		assertEquals(valid, c.getChunk());
	}

	/**
	 * Test method for toString()
	 */
	@Test
	public void testToString() {
		String invalid = "XXXXXXXX";
		String valid = "1234ABCD";
		TrackChunk c = null;
		try {
			c = new TrackChunk();
		} catch (MalformedTrackException e) {
			fail();
		}
		
		try {
			c.setChunk(invalid);
			fail();
		} catch (MalformedTrackException e) {
			assertEquals(ERROR_MSG, e.getMessage());
		}
		assertEquals(DEFAULT, c.toString());
		
		try {
			c.setChunk(valid);
		} catch (MalformedTrackException e) {
			fail();
		}
		assertEquals(valid, c.toString());
	}
}
