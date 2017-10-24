package edu.ncsu.csc216.howlondemand.model;

/**
 * Abstract parent class of AudioTrack; used to make implementation 
 * of streaming of other media types possible.
 * @author Noah Benveniste
 */
public abstract class Multimedia {
	/** */
	protected int id;
	/** */
	protected String title;
	
	/**
	 * 
	 * @param id
	 * @param title
	 */
	public Multimedia(int id, String title) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * 
	 */
	public void setId(int id) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract TrackChunk getNextChunk();
	
	/**
	 * 
	 */
	public abstract boolean hasNextChunk();
	
	/**
	 * 
	 * @param c
	 */
	public abstract void addChunk(TrackChunk c);
	
	/**
	 * 
	 */
	public abstract String toString();
}
