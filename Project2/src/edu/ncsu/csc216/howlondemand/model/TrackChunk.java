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
	 * 
	 */
	public TrackChunk() {
		
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
	 * @param chunk
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
	 * 
	 */
	public String getChunk() {
		return this.chunk;
	}
	
	/**
	 * 
	 * @param chunk
	 */
	public boolean validChunk(String chunk) {
		if (chunk == null) {
			return false;
		} else if () {
			
		}
	}
}
