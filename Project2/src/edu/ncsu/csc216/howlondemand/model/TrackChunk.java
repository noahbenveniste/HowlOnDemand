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
	 * Default constructor for the TrackChunk
	 */
	public TrackChunk() {
		//TODO check that this is supposed to just be empty
	}
	
	/**
	 * 
	 * @throws MalformedTrackException 
	 */
	public TrackChunk(String chunk) throws MalformedTrackException {
		this();
		this.setChunk(chunk);
	}
	
	/**
	 * 
	 * @param chunk the 
	 * @throws MalformedTrackException 
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
