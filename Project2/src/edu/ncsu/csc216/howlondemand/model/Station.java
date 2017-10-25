package edu.ncsu.csc216.howlondemand.model;

import java.util.ArrayList;

import edu.ncsu.csc216.audioxml.xml.StationXML;

/**
 * 
 * @author Noah Benveniste
 */
public class Station {
	/** A collection of AudioTrack objects that the Station can stream */
	private ArrayList<AudioTrack> playlist;
	/** The station's ID number */
	private int id;
	/** The station's title */
	private String title;
	/** The station's repeat toggle status */
	private boolean repeat;
	/** The station's shuffle toggle status */
	private boolean shuffle;
	/** A numerical value corresponding to the station's color theme */
	private int color;
	/** Index of the current AudioTrack */
	private int index;
	
	/**
	 * 
	 * @param id
	 * @param title
	 * @param color
	 */
	public Station(int id, String title, int color) {
		
	}
	
	/**
	 * 
	 * @param s
	 */
	public Station(StationXML s) {
		
	}

	/**
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return the repeat
	 */
	public boolean getRepeat() {
		return repeat;
	}

	/**
	 * 
	 * @param repeat the repeat to set
	 */
	public void toggleRepeat() {
		repeat = !repeat;
	}

	/**
	 * 
	 * @return the shuffle
	 */
	public boolean getShuffle() {
		return shuffle;
	}

	/**
	 * 
	 * @param shuffle the shuffle to set
	 */
	public void toggleShuffle() {
		shuffle = !shuffle;
	}

	/**
	 * 
	 * @return the playlist
	 */
	public ArrayList<AudioTrack> getPlaylist() {
		return playlist;
	}
	
	/**
	 * 
	 * @param t the AudioTrack to add to the playlist
	 */
	public void addAudioTrack(AudioTrack t) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasNextTrack() {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public AudioTrack getCurrentAudioTrack() {
		return null;
	}
	
	/**
	 * 
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * 
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 
	 */
	public void reset() {
		
	}

	/**
	 * 
	 * @return
	 */
	public String toString() {
		return null;
	}
}
