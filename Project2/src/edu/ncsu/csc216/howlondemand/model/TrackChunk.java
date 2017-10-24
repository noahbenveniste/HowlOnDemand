package edu.ncsu.csc216.howlondemand.model;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;

/**
 * 
 * @author Noah Benveniste
 */
public class TrackChunk {
	/** A string of hexadecimal values */
	private String chunk;
	
	/**
	 * Default constructor for the TrackChunk. Chunk field is initialized to an empty string
	 * by default to avoid NullPointerExceptions. The chunk field must be set with a call to
	 * setChunk() if this constructor is used.
	 */
	public TrackChunk() {
		this.chunk = "";
	}
	
	/**
	 * Constructor that creates a TrackChunk with a specified chunk string. A chunk string must contain
	 * exactly 8 valid hexadecimal digits i.e. 0-9,A-F.
	 * @throws MalformedTrackException if the chunk string does not meet the aforementioned standards.
	 */
	public TrackChunk(String chunk) throws MalformedTrackException {
		this();
		this.setChunk(chunk);
	}
	
	/**
	 * Sets the chunk field with a specified chunk string.
	 * @param chunk a string of exactly 8 valid hexadecimal digits i.e. 0-9,A-F
	 * @throws MalformedTrackException if the specified chunk does not conform to the aforementioned standards.
	 */
	public void setChunk(String chunk) throws MalformedTrackException {
		if (validChunk(chunk)) {
			this.chunk = chunk;
		} else {
			throw new MalformedTrackException();
		}
	}
	
	/**
	 * Returns the chunk field
	 * @return the chunk field
	 */
	public String getChunk() {
		return this.chunk;
	}
	
	/**
	 * Used to validate a chunk string. A chunk string is valid only if it is exactly 8 characters, with those
	 * characters only consisting of valid hexadecimal digits i.e. 0-9,A-F
	 * @param chunk the track chunk to validate
	 * @return true if the chunk is valid, false if the input is null or it does not conform to the standards described above.
	 */
	public boolean validChunk(String chunk) {
		String regex = "([0-9]||[A-F]){8}"; //Indicates that the chunk must contain exactly 8 characters that can range from 0-9,A-F
		if (chunk == null) {
			return false;
		} else if (!chunk.matches(regex)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns a string representation of the TrackChunk
	 * @return a string representation of the TrackChunk
	 */
	public String toString() {
		//TODO make sure that this format is correct
		return this.chunk;
	}
}
