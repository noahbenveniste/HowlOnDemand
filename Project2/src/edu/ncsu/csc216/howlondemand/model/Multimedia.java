package edu.ncsu.csc216.howlondemand.model;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;

/**
 * Abstract parent class of AudioTrack; used to make implementation of streaming of 
 * other media types possible. Multimedia objects have a numerical ID and a
 * title to reference them by.
 * @author Noah Benveniste
 */
public abstract class Multimedia {
	/** The Multimedia object's ID number */
	protected int id;
	/** The Multimedia object's title */
	protected String title;
	
	/**
	 * Superclass constructor for Multimedia child objects; sets the id
	 * and title field for the created Multimedia child object
	 * @param id the numerical ID
	 * @param title the title
	 * @throws IllegalArgumentException if the ID is negative or the title is null
	 */
	public Multimedia(int id, String title) {
		setId(id);
		setTitle(title);
	}
	
	/**
	 * Gets the ID number for the Multimedia object
	 * @return the ID number
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the ID number
	 * @param id the ID number to set
	 * @throws IllegalArgumentException if the ID is negative
	 */
	public void setId(int id) throws IllegalArgumentException {
		if (id < 0) {
			throw new IllegalArgumentException("Track ID is invalid, cannot be negative");
		} else {
			this.id = id;
		}
	}
	
	/**
	 * Gets the title of the Multimedia object
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Sets the title of the Multimedia object
	 * @param title the title to set
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setTitle(String title) throws IllegalArgumentException {
		if (title == null) {
			throw new IllegalArgumentException("Track title cannot be null");
		} else {
			this.title = title;
		}
	}
	
	/**
	 * Gets the next chunk in the Multimedia object's chunk collection
	 * @return the next chunk
	 * @throws IllegalArgumentException if there are no chunks left in the collection that haven't
	 * been accessed yet.
	 */
	public abstract TrackChunk getNextChunk();
	
	/**
	 * Checks if there are chunks left in the Multimedia object's collection of chunks
	 * @return true if there are chunks left, false if the Multimedia object has
	 * reached the end of its chunk collection.
	 */
	public abstract boolean hasNextChunk();
	
	/**
	 * Adds a chunk to the Multimedia object's chunk collection
	 * @param c the chunk to add
	 * @throws MalformedTrackException if the chunk data is bad
	 */
	public abstract void addChunk(TrackChunk c) throws MalformedTrackException ;
	
	/**
	 * Gets a string representation of the Multimedia object
	 * @return the string representation
	 */
	public abstract String toString();
}
