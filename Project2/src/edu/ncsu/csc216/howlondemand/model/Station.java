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
	 * Constructs a Station from scratch with an empty track list; also initializes
	 * shuffle and repeat to off.
	 * @param id the Station's ID number
	 * @param title the Station's title
	 * @param color a number corresponding to the Station's color theme
	 */
	public Station(int id, String title, int color) {
		setId(id);
		setTitle(title);
		setColor(color);
		playlist = new ArrayList<AudioTrack>();
		reset();
	}
	
	/**
	 * Constructs a Station object using data from a StationXML object. Data used
	 * include ID number, title, color, shuffle status, repeat status, and any AudioTrackXML
	 * objects (creates AudioTrack objects from them)
	 * @param s the StationXML object used to create a Station object
	 */
	public Station(StationXML s) {
		this(s.getId(), s.getTitle(), s.getColor());
		shuffle = s.isShuffle();
		repeat = s.isRepeat();
	}

	/**
	 * Gets the Station's ID
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the Station's ID
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the Station's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Station's title
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the Station's current repeat toggle value
	 * @return the repeat
	 */
	public boolean getRepeat() {
		return repeat;
	}

	/**
	 * Toggles repeat from on to off or off to on
	 */
	public void toggleRepeat() {
		repeat = !repeat;
	}

	/**
	 * Gets the Station's current shuffle toggle value
	 * @return the shuffle
	 */
	public boolean getShuffle() {
		return shuffle;
	}

	/**
	 * Toggles the shuffle from on to off or off to on
	 */
	public void toggleShuffle() {
		shuffle = !shuffle;
	}

	/**
	 * Gets the Station's playlist of AudioTracks
	 * @return the playlist as an ArrayList<AudioTrack>
	 */
	public ArrayList<AudioTrack> getPlaylist() {
		return playlist;
	}
	
	/**
	 * Adds an AudioTrack to the playlist
	 * @param t the AudioTrack to add to the playlist
	 */
	public void addAudioTrack(AudioTrack t) {
		
	}
	
	/**
	 * Checks if the Station has anymore AudioTracks in its playlist
	 * @return
	 */
	public boolean hasNextTrack() {
		return false;
	}
	
	/**
	 * Gets the currently indexed AudioTrack
	 * @return the current AudioTrack
	 */
	public AudioTrack getCurrentAudioTrack() {
		return playlist.get(index);
	}
	
	/**
	 * Gets the number corresponding to the Station's color theme
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Sets the number corresponding to the Station's color theme
	 * @param color the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * Gets the index of the next AudioTrack to be indexed by the Station
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index of the next AudioTrack in the Station's playlist to be indexed
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Resets the station back to its initial state i.e. the current track index is set
	 * back to 0 and shuffle and repeat are both set back to false.
	 */
	public void reset() {
		index = 0;
		shuffle = false;
		repeat = false;
	}

	/**
	 * Returns a string representation of the Station in the form of <ID>,<Title>,<Color>
	 * @return the string representation of the Station
	 */
	public String toString() {
		return "" + getId() + "," + getTitle() + "," + getColor();
	}
}
