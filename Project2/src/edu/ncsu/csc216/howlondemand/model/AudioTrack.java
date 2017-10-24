package edu.ncsu.csc216.howlondemand.model;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.audioxml.xml.AudioTrackXML;
import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.TrackChunkXML;

/**
 * 
 * @author Noah Benveniste
 */
public class AudioTrack extends Multimedia {
	/** */
	private int chunkIndex;
	/** */
	private String artist;
	/** */
	private ArrayList<TrackChunk>  chunks;
	
	/**
	 * 
	 * @param id
	 * @param title
	 * @param artist
	 */
	public AudioTrack(int id, String title, String artist) {
		//Set id and title using Multimedia super constructor
		super(id, title);
		//Set the artist field
		setArtist(artist);
		//Initialized to zero when the object is created
		chunkIndex = 0; 
		//Initialize an empty ArrayList of TrackChunks
		chunks = new ArrayList<TrackChunk>();
	}
	
	/**
	 * Constructs an AudioTrack by pulling data from an AudioTrackXML. Data used
	 * includes id, title, artist and any track chunk data.
	 * @param track the AudioTrackXML object to be used to create the AudioTrack
	 */
	public AudioTrack(AudioTrackXML track) throws MalformedTrackException {
		//Set the id, title and artist, initialize the TrackChunk array and chunkIndex value using other constructor.
		this(track.getId(), track.getTitle(), track.getArtist());
		TrackChunkXML chunk = track.getTrackChunks();
		if (chunk != null) { //To avoid NPEs
			//TrackChunkXML contains a list of strings that correspond to individual track chunks
			List<String> chunkStrings = chunk.getChunk();
			for (int i = 0; i < chunkStrings.size(); i++) {
				//Create a new TrackChunk using the string, add it to the chunks ArrayList
				addChunk(new TrackChunk(chunkStrings.get(i))); 
			}
		}
	}
	
	/**
	 * Gets the track's artist
	 * @return the artist
	 */
	public String getArtist() {
		return this.artist;
	}
	
	/**
	 * Sets the AudioTrack's artist field
	 * @throws IllegalArgumentException if the input is null
	 */
	public void setArtist(String artist) {
		if (artist == null) {
			throw new IllegalArgumentException("Artist cannot be null");
		} else {
			this.artist = artist;
		}
	}
	
	/**
	 * Gets the index of the most recently pulled chunk in the chunk collection
	 * @return the index
	 */
	public int getChunkIndex() {
		return this.chunkIndex;
	}
	
	/**
	 * Sets the chunk index, i.e. the index of the most recently accessed chunk in
	 * the AudioTrack's chunk collection
	 * @throws IllegalArgumentException if the index is out of bounds i.e. it is negative
	 * or greater than the size of the chunk collection
	 */
	public void setChunkIndex(int idx) {
		if (idx < 0 || idx > chunks.size()) {
			throw new IllegalArgumentException("Chunk index cannot be less than zero or greater than the size of the chunk collection");
		} else {
			this.chunkIndex = idx;
		}
	}
	
	/**
	 * Gets the number of chunks in the AudioTrack's chunk collection
	 * @return the number of chunks
	 */
	public int getTrackChunkSize() {
		return chunks.size();
	}
	
	/**
	 * Gets the next chunk in the AudioTrack's chunk collection
	 * @return the next chunk
	 * @throws IllegalArgumentException if there are no chunks left in the collection that haven't
	 * been accessed yet.
	 */
	@Override
	public TrackChunk getNextChunk() {
		//Check that the current chunkIndex is less than the chunk collection's size. If not, throw an IAE
		if (chunkIndex >= chunks.size()) {
			throw new IllegalArgumentException("No chunks left in track's chunk collection");
		} else {
			//If the chunkIndex is less than the chunk collection's size, return the chunk at that index, increment the chunkIndex
			TrackChunk c = chunks.get(chunkIndex);
			chunkIndex++;
			return c;
		}
	}

	/**
	 * Checks if there are chunks left in the AudioTrack's collection of chunks
	 * @return true if there are chunks left, false if the AudioTrack has
	 * reached the end of its chunk collection.
	 */
	@Override
	public boolean hasNextChunk() {
		return (this.chunkIndex < this.chunks.size());
	}

	/**
	 * Adds a chunk to the AudioTrack's chunk collection
	 * @param c the chunk to add
	 */
	@Override
	public void addChunk(TrackChunk c) {
		chunks.add(c);
	}

	/**
	 * Gets a string representation of the AudioTrack
	 * @return the string representation
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
