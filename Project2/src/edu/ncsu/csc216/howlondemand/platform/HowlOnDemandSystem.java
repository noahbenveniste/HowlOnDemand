package edu.ncsu.csc216.howlondemand.platform;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationIOException;
import edu.ncsu.csc216.audioxml.xml.StationXML;
import edu.ncsu.csc216.audioxml.xml.StationsReader;
import edu.ncsu.csc216.howlondemand.model.AudioTrack;
import edu.ncsu.csc216.howlondemand.model.Station;
import edu.ncsu.csc216.howlondemand.model.TrackChunk;
import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

/**
 * Finite state machine that manages track selection from a selected station and
 * the streaming of those tracks. HowlOnDemandSystem follows the singleton design
 * pattern, meaning that only one instance can exist at a given time.
 * @author Noah Benveniste
 */
public class HowlOnDemandSystem {
	/** The capacity of the system buffer */
	public static final int BUFFER_CAPACITY = 100;
	/** The number of stations the system can hold (based on the GUI display) */
	public static final int STATION_CAPACITY = 9;
	/** The name of the SelectionState */
	public static final String SELECTION_NAME = "Selection";
	/** The name of the PlayWithBufferingState */
	public static final String PLAYWITHBUFFERING_NAME = "Playing with Buffering";
	/** The name of the PlayWithoutBufferingState */
	public static final String PLAYWITHOUTBUFFERING_NAME = "Playing without Buffering";
	/** The name of the StopWithBufferingState */
	public static final String STOPWITHBUFFERING_NAME = "Stopped with Buffering";
	/** The name of the StopWithoutBufferingState */
	public static final String STOPWITHOUTBUFFERING_NAME = "Stopped without Buffering";
	/** The name of the QuitState */
	public static final String QUIT_NAME = "Quit";
	/** The name of the FinishedState */
	public static final String FINISHED_NAME = "Finished";
	
	/** SelectionState object for the FSM */
	private HowlOnDemandSystemState selectionState;
	/** PlayWithBufferingState object for the FSM */
	private HowlOnDemandSystemState playWithBufferingState;
	/** PlayWithoutBufferingState object for the FSM */
	private HowlOnDemandSystemState playWithoutBufferingState;
	/** StopWithBufferingState object for the FSM */
	private HowlOnDemandSystemState stopWithBufferingState;
	/** StopWithoutBUfferingState object for the FSM */
	private HowlOnDemandSystemState stopWithoutBufferingState;
	/** QuitState object for the FSM */
	private HowlOnDemandSystemState quitState;
	/** FinishedState object for the FSM */
	private HowlOnDemandSystemState finishedState;
	/** Current state of the FSM */
	private HowlOnDemandSystemState state;
	
	/** The instance of the HowlOnDemandSystem that the GUI will work with */
	private static HowlOnDemandSystem singleton;
	/** The current station that is being streamed */
	private Station currentStation;
	/** A collection of stations available to stream */
	private ArrayList<Station> stations;
	/** The chunk buffer, used for streaming AudioTracks */
	private Queue<TrackChunk> chunks;
	
	/**
	 * Used to access the singleton instance of the HowlOnDemandSystem object.
	 * If the method hasn't been called before, a new instance of the object is
	 * created. Otherwise, a reference to the already created instance of the
	 * HowlOnDemandSystem is returned.
	 * @return the singleton instance of the HowlOnDemandSystem
	 */
	public static HowlOnDemandSystem getInstance() {
		if (singleton == null) {
			//Only call the constructor to create a new object if the static instance field is null
			singleton = new HowlOnDemandSystem();
		}
		return singleton;
	}
	
	/**
	 * Constructs a HowlOnDemandSystem object. Initializes all state objects,
	 * initializes the current state to the selection state, and finally initializes
	 * the Station and TrackChunk collections.
	 */
	private HowlOnDemandSystem() {
		//Initialize all state fields for the FSM
		selectionState = new SelectionState();
		playWithBufferingState = new PlayWithBufferingState();
		playWithoutBufferingState = new PlayWithoutBufferingState();
		stopWithBufferingState = new StopWithBufferingState();
		stopWithoutBufferingState = new StopWithoutBufferingState();
		quitState = new QuitState();
		finishedState = new FinishedState();
		
		//Initialize the current state to the selection state
		state = selectionState;
		
		//Initialize the stations ArrayList
		stations = new ArrayList<Station>();
		
		//Initialize the track chunk queue
		chunks = new LinkedList<TrackChunk>();
	}
	
	/**
	 * Creates a collection of Stations from an XML file.
	 * @param fileName the name of the file
	 * @throws StationIOException if there is an issue reading in the XML file
	 * @throw IllegalArgumentException if more than 9 stations are loaded
	 * @throw MalformedTrackException if any of the stations contain bad track data
	 */
	public void loadStationsFromFile(String fileName) throws StationIOException, MalformedTrackException {
		//Reset stations field
		stations = new ArrayList<Station>();
		
		StationsReader stationsReader = new StationsReader(fileName); //Throws StationIOException if there is an issue processing the XML file
		
		//Get a list of StationXML objects from the Stations if the reader read in the objects successfully from the file
		List<StationXML> temp = stationsReader.getStations();
		
		if (temp.size() > STATION_CAPACITY) {
			throw new IllegalArgumentException("HowlOnDemandSystem can only accommodate up to 9 stations");
		}
		
		//Attempt to process the StationXML objects and add them to the stations collection
		for (int i = 0; i < temp.size(); i++) {
			stations.add(new Station(temp.get(i))); //Throws MalformedTrackException if the Station contains bad data
		}
	}
	
	/**
	 * Gets the system's station collection
	 * @return the collection of Stations
	 */
	public ArrayList<Station> getStations() {
		return stations;
	}
	
	/**
	 * Sets the current station
	 * @param s the station to be the new current station
	 * @throws IllegalArgumentException if the station passed is null
	 */
	public void loadStation(Station s) {
		if (s == null) {
			throw new IllegalArgumentException("Cannot set the current station to null");
		} else {
			currentStation = s;
			currentStation.setIndex(0);
		}
	}
	
	/**
	 * Gets the current station
	 * @return the current station
	 */
	public Station getCurrentStation() {
		return currentStation;
	}
	
	/**
	 * Gets the currently index audio track for the current station
	 * @return the current audio track
	 */
	public AudioTrack getCurrentAudioTrack() {
		if (currentStation == null) {
			throw new IllegalArgumentException("Current station not set");
		}
		return currentStation.getCurrentAudioTrack();
	}
	
	/**
	 * Empties system buffer and station list, nulls the current station, sets the state back to selection state
	 */
	public void reset() {
		state = selectionState;
		chunks = new LinkedList<TrackChunk>();
		//Must reset the current station before nulling the field
		if (currentStation != null) {
			currentStation.reset();
			currentStation = null;
		}
		//Reset stations field
		stations = new ArrayList<Station>();
	}
	
	/**
	 * Returns a string that contains the FSM's current state and the number of
	 * TrackChunks in the buffer in the form HowlOnDemandSystem [state=<currentState>, chunks.size()=<number of chunks>]
	 * @return a string that contains the current state and the number of elements in the buffer
	 */
	public String toString() {
		return "HowlOnDemandSystem [state=" + getState().getStateName() + ", chunks.size()=" + getChunkSize() + "]";
	}
	
	/**
	 * Gets the number of chunks currently in the buffer
	 * @return the number of chunks in the buffer
	 */
	public int getChunkSize() {
		return chunks.size();
	}
	
	/**
	 * Consumes a chunk by removing the chunk at the front of the buffer
	 * @return the consumed track chunk
	 * @throws IllegalArgumentException if the buffer is empty
	 */
	public TrackChunk consumeTrackChunk() {
		if (hasNextTrackChunk()) {
			return chunks.remove();
		} else {
			throw new IllegalArgumentException("Buffer is empty");
		}
	}
	
	/**
	 * Checks if the buffer has more chunks
	 * @return true if it does, false if its empty
	 */
	public boolean hasNextTrackChunk() {
		return (chunks.size() > 0 );
	}
	
	/**
	 * Attempts to add a track chunk to the buffer
	 * @param c the chunk to add
	 * @throws IllegalArgumentException if there is no room left in the buffer
	 */
	public void addTrackChunkToBuffer(TrackChunk c) {
		if (bufferHasRoom()) {
			//If the buffer has room, add the chunk
			chunks.add(c);
		} else {
			//If the buffer is full, throw an IAE
			throw new IllegalArgumentException("Buffer is full");
		}
	}
	
	/**
	 * Checks if the buffer has room for more chunks
	 * @return true if it does, false if it's full
	 */
	public boolean bufferHasRoom() {
		return (chunks.size() < BUFFER_CAPACITY);
	}
	
	/**
	 * Gets the current state of the HowlOnDemandSystem FSM
	 * @return the current state
	 */
	public HowlOnDemandSystemState getState() {
		return state;
	}
	
	/**
	 * Accepts user commands from the GUI and passes them to the internal FSM
	 * @param c the user's command
	 * @throws UnsupportedOperationException if for the given state, the command is invalid
	 */
	public void executeCommand(Command c) {
		state.updateState(c); //If this throws an UnsupportedOperationException, no internal state should be changed
	}
	
	/**
	 * Starting state for the system where the user is selecting
	 * @author Noah Benveniste
	 */
	private class SelectionState implements HowlOnDemandSystemState {
		/**
		 * Updates the system's current state based on the input command and the current state of the system
		 * @throws UnsupportedOperationException if the command is invalid
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.PLAY) {
				//Add the first chunk from the current track from the current station to the buffer
				addTrackChunkToBuffer(getCurrentAudioTrack().getNextChunk());
				//Update the state to PLAY WITH BUFFERING
				state = playWithBufferingState;
			} else {
				//Any other command is invalid
				throw new UnsupportedOperationException(c.getCommand().toString() + " is not a valid command for " + this.getStateName());
			}
		}

		/**
		 * Gets the name of this state
		 * @return the name of the state as a string
		 */
		@Override
		public String getStateName() {
			return SELECTION_NAME;
		}
	}
	
	/**
	 * The system is playing while the buffer is filling with track chunks
	 * @author Noah Benveniste
	 */
	private class PlayWithBufferingState implements HowlOnDemandSystemState {
		/**
		 * Updates the system's current state based on the input command and the current state of the system
		 * @throws UnsupportedOperationException if the command is invalid
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.BUFFERING) {
				//If the current audio track has another track chunk, try to add it to the buffer
				if (getCurrentAudioTrack().hasNextChunk()) {
					//If the buffer is full, consume the next chunk, don't add a chunk to the buffer
					if (!bufferHasRoom()) {
						consumeTrackChunk();
						state = playWithoutBufferingState;
					} else {
						//The buffer has room, add a chunk
						addTrackChunkToBuffer(getCurrentAudioTrack().getNextChunk());
						//The buffer has chunks, consume a chunk
						consumeTrackChunk();
						state = playWithBufferingState;
					}
				} else { //If the track has no more chunks
					//If the buffer is empty
					if (chunks.isEmpty()) {
						state = finishedState;
					} else {
						//Track has no more chunks, but the buffer is not empty
						consumeTrackChunk();
						state = playWithoutBufferingState;
					}
				}
				
			} else if (c.getCommand() == CommandValue.NOT_BUFFERING) {
				//Since the system isn't buffering, don't try to add chunks to the buffer, only remove
				if (chunks.isEmpty() && !getCurrentAudioTrack().hasNextChunk()) {
					state = finishedState;
				} else {
					consumeTrackChunk();
					state = playWithoutBufferingState;
				}
				
			} else if (c.getCommand() == CommandValue.STOP) {
				state = stopWithBufferingState;
				
			} else if (c.getCommand() == CommandValue.RETURN) {
				state = quitState;
				
			} else if (c.getCommand() == CommandValue.SKIP_FORWARD) {
				//If the current station has its shuffle option toggled
				if (currentStation.getShuffle()) {
					//Randomly generate a new index using a while loop to ensure that its different
					int newTrackIdx = currentStation.getIndex();
					Random r = new Random();
					//Loop repeats until a number is generate that is different than the current track index
					while (newTrackIdx == currentStation.getIndex()) {
						//Generates a number between [0, currentStation.getPlaylist().size() )
						newTrackIdx = r.nextInt(currentStation.getPlaylist().size());
					}
					//Change the new current track for the station to the one at the randomly generated index
					currentStation.setIndex(newTrackIdx);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else if (currentStation.hasNextTrack()) {
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current++);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else {
					//Current station has no more tracks, check if the playlist is set to repeat
					if (currentStation.getRepeat()) {
						currentStation.setIndex(0);
						//Set the station's new current track's chunk index to 0
						currentStation.getCurrentAudioTrack().setChunkIndex(0);
						//Clear the buffer
						chunks = new LinkedList<TrackChunk>();
					} else {
						state = finishedState;
					}
				}
				
			} else if (c.getCommand() == CommandValue.SKIP_BACKWARD) {
				//If the current track is the first track in the station
				if (currentStation.getIndex() == 0) {
					//If the first track is playing, restart it
					getCurrentAudioTrack().setChunkIndex(0);
					chunks = new LinkedList<TrackChunk>();
				} else {
					//If anything other than the first track is playing, decrement the track index
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current--);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks = new LinkedList<TrackChunk>();
				}
				
				
			} else {
				//Any other command is invalid
				throw new UnsupportedOperationException(c.getCommand().toString() + " is not a valid command for " + this.getStateName());
			}
		}

		/**
		 * Gets the name of this state
		 * @return the name of the state as a string
		 */
		@Override
		public String getStateName() {
			return PLAYWITHBUFFERING_NAME;
		}
	}
	
	/**
	 * The system is playing but the buffer is not filling with track chunks
	 * @author Noah Benveniste
	 */
	private class PlayWithoutBufferingState implements HowlOnDemandSystemState {
		/**
		 * Updates the system's current state based on the input command and the current state of the system
		 * @throws UnsupportedOperationException if the command is invalid
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.BUFFERING) {
				if (bufferHasRoom()) {
					if (!chunks.isEmpty()) {
						consumeTrackChunk();
					}
					if (getCurrentAudioTrack().hasNextChunk()) {
						addTrackChunkToBuffer(getCurrentAudioTrack().getNextChunk());
						state = playWithBufferingState;
					}
				}
				
			} else if (c.getCommand() == CommandValue.NOT_BUFFERING) {
				if (!chunks.isEmpty()) {
					consumeTrackChunk();
				} else {
					if (!getCurrentAudioTrack().hasNextChunk()) {
						state = finishedState;
					}
				}
				
			} else if (c.getCommand() == CommandValue.STOP) {
				state = stopWithoutBufferingState;
				
			} else if (c.getCommand() == CommandValue.RETURN) {
				state = quitState;
				
			} else if (c.getCommand() == CommandValue.SKIP_FORWARD) {
				//If the current station has its shuffle option toggled
				if (currentStation.getShuffle()) {
					//Randomly generate a new index using a while loop to ensure that its different
					int newTrackIdx = currentStation.getIndex();
					Random r = new Random();
					//Loop repeats until a number is generate that is different than the current track index
					while (newTrackIdx == currentStation.getIndex()) {
						//Generates a number between [0, currentStation.getPlaylist().size() )
						newTrackIdx = r.nextInt(currentStation.getPlaylist().size());
					}
					//Change the new current track for the station to the one at the randomly generated index
					currentStation.setIndex(newTrackIdx);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else if (currentStation.hasNextTrack()) {
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current++);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else {
					//Current station has no more tracks, check if the playlist is set to repeat
					if (currentStation.getRepeat()) {
						currentStation.setIndex(0);
						//Set the station's new current track's chunk index to 0
						currentStation.getCurrentAudioTrack().setChunkIndex(0);
						//Clear the buffer
						chunks = new LinkedList<TrackChunk>();
					} else {
						state = finishedState;
					}
				}
				
			} else if (c.getCommand() == CommandValue.SKIP_BACKWARD) {
				//If the current track is the first track in the station
				if (currentStation.getIndex() == 0) {
					//If the first track is playing, restart it
					getCurrentAudioTrack().setChunkIndex(0);
					chunks = new LinkedList<TrackChunk>();
				} else {
					//If anything other than the first track is playing, decrement the track index
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current--);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks = new LinkedList<TrackChunk>();
				}
				
			} else {
				//Any other command is invalid
				throw new UnsupportedOperationException(c.getCommand().toString() + " is not a valid command for " + this.getStateName());
			}
			
		}

		/**
		 * Gets the name of this state
		 * @return the name of the state as a string
		 */
		@Override
		public String getStateName() {
			return PLAYWITHOUTBUFFERING_NAME;
		}
	}
	
	/**
	 * The system has stopped streaming chunks but the buffer is filling with chunks
	 * @author Noah Benveniste
	 */
	private class StopWithBufferingState implements HowlOnDemandSystemState {
		/**
		 * Updates the system's current state based on the input command and the current state of the system
		 * @throws UnsupportedOperationException if the command is invalid
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.BUFFERING) {
				if (getCurrentAudioTrack().hasNextChunk()) {
					if (bufferHasRoom()) {
						addTrackChunkToBuffer(getCurrentAudioTrack().getNextChunk());
					} else {
						state = stopWithoutBufferingState;
					}
				} else {
					state = stopWithoutBufferingState;
				}
				
			} else if (c.getCommand() == CommandValue.NOT_BUFFERING) {
				state = stopWithoutBufferingState;
				
			} else if (c.getCommand() == CommandValue.PLAY) {
				state = playWithBufferingState;
				
			} else if (c.getCommand() == CommandValue.RETURN) {
				state = quitState;
				
			} else if (c.getCommand() == CommandValue.SKIP_FORWARD) {
				//If the current station has its shuffle option toggled
				if (currentStation.getShuffle()) {
					//Randomly generate a new index using a while loop to ensure that its different
					int newTrackIdx = currentStation.getIndex();
					Random r = new Random();
					//Loop repeats until a number is generate that is different than the current track index
					while (newTrackIdx == currentStation.getIndex()) {
						//Generates a number between [0, currentStation.getPlaylist().size() )
						newTrackIdx = r.nextInt(currentStation.getPlaylist().size());
					}
					//Change the new current track for the station to the one at the randomly generated index
					currentStation.setIndex(newTrackIdx);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else if (currentStation.hasNextTrack()) {
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current++);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else {
					//Current station has no more tracks, check if the playlist is set to repeat
					if (currentStation.getRepeat()) {
						currentStation.setIndex(0);
						//Set the station's new current track's chunk index to 0
						currentStation.getCurrentAudioTrack().setChunkIndex(0);
						//Clear the buffer
						chunks = new LinkedList<TrackChunk>();
					} else {
						state = finishedState;
					}
				}
				
			} else if (c.getCommand() == CommandValue.SKIP_BACKWARD) {
				//If the current track is the first track in the station
				if (currentStation.getIndex() == 0) {
					//If the first track is playing, restart it
					getCurrentAudioTrack().setChunkIndex(0);
					chunks = new LinkedList<TrackChunk>();
				} else {
					//If anything other than the first track is playing, decrement the track index
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current--);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks = new LinkedList<TrackChunk>();
				}
				
			} else {
				//Any other command is invalid
				throw new UnsupportedOperationException(c.getCommand().toString() + " is not a valid command for " + this.getStateName());
			}
		}

		/**
		 * Gets the name of this state
		 * @return the name of the state as a string
		 */
		@Override
		public String getStateName() {
			return STOPWITHBUFFERING_NAME;
		}
	}
	
	/**
	 * The system is not streaming and the buffer is not filling with chunks
	 * @author Noah Benveniste
	 */
	private class StopWithoutBufferingState implements HowlOnDemandSystemState {
		/**
		 * Updates the system's current state based on the input command and the current state of the system
		 * @throws UnsupportedOperationException if the command is invalid
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.BUFFERING) {
				state = stopWithBufferingState;
				
			} else if (c.getCommand() == CommandValue.NOT_BUFFERING) {
				state = stopWithoutBufferingState;
				
			} else if (c.getCommand() == CommandValue.PLAY) {
				if (!chunks.isEmpty()) {
					consumeTrackChunk();
					state = playWithBufferingState;
				} else {
					if (!getCurrentAudioTrack().hasNextChunk()) {
						state = finishedState;
					}
				}
				
			} else if (c.getCommand() == CommandValue.RETURN) {
				state = quitState;
				
			} else if (c.getCommand() == CommandValue.SKIP_FORWARD) {
				//If the current station has its shuffle option toggled
				if (currentStation.getShuffle()) {
					//Randomly generate a new index using a while loop to ensure that its different
					int newTrackIdx = currentStation.getIndex();
					Random r = new Random();
					//Loop repeats until a number is generate that is different than the current track index
					while (newTrackIdx == currentStation.getIndex()) {
						//Generates a number between [0, currentStation.getPlaylist().size() )
						newTrackIdx = r.nextInt(currentStation.getPlaylist().size());
					}
					//Change the new current track for the station to the one at the randomly generated index
					currentStation.setIndex(newTrackIdx);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else if (currentStation.hasNextTrack()) {
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current++);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else {
					//Current station has no more tracks, check if the playlist is set to repeat
					if (currentStation.getRepeat()) {
						currentStation.setIndex(0);
						//Set the station's new current track's chunk index to 0
						currentStation.getCurrentAudioTrack().setChunkIndex(0);
						//Clear the buffer
						chunks = new LinkedList<TrackChunk>();
					} else {
						state = finishedState;
					}
				}
				
			} else if (c.getCommand() == CommandValue.SKIP_BACKWARD) {
				//If the current track is the first track in the station
				if (currentStation.getIndex() == 0) {
					//If the first track is playing, restart it
					getCurrentAudioTrack().setChunkIndex(0);
					chunks = new LinkedList<TrackChunk>();
				} else {
					//If anything other than the first track is playing, decrement the track index
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current--);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks = new LinkedList<TrackChunk>();
				}
				
			} else {
				//Any other command is invalid
				throw new UnsupportedOperationException(c.getCommand().toString() + " is not a valid command for " + this.getStateName());
			}
		}
		
		/**
		 * Gets the name of this state
		 * @return the name of the state as a string
		 */
		@Override
		public String getStateName() {
			return STOPWITHOUTBUFFERING_NAME;
		}
	}
	
	/**
	 * The system has quit, the instance is reset
	 * @author Noah Benveniste
	 */
	private class QuitState implements HowlOnDemandSystemState {
		/**
		 * Updates the system's current state based on the input command and the current state of the system
		 * @throws UnsupportedOperationException if the command is invalid
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.SELECT_STATION) {
				reset();
				
			} else {
				//Any other command is invalid
				throw new UnsupportedOperationException(c.getCommand().toString() + " is not a valid command for " + this.getStateName());
			}
		}

		/**
		 * Gets the name of this state
		 * @return the name of the state as a string
		 */
		@Override
		public String getStateName() {
			return QUIT_NAME;
		}
	}
	
	/**
	 * The system has finished streaming a track
	 * @author Noah Benveniste
	 */
	private class FinishedState implements HowlOnDemandSystemState {		
		/**
		 * Updates the system's current state based on the input command and the current state of the system
		 * @throws UnsupportedOperationException if the command is invalid
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() == CommandValue.FINISH_TRACK) {
				if (currentStation.getShuffle()) {
					//Randomly generate a new index using a while loop to ensure that its different
					int newTrackIdx = currentStation.getIndex();
					Random r = new Random();
					//Loop repeats until a number is generate that is different than the current track index
					while (newTrackIdx == currentStation.getIndex()) {
						//Generates a number between [0, currentStation.getPlaylist().size() )
						newTrackIdx = r.nextInt(currentStation.getPlaylist().size());
					}
					//Change the new current track for the station to the one at the randomly generated index
					currentStation.setIndex(newTrackIdx);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else if (currentStation.hasNextTrack()) {
					//Increment the current index
					int current = currentStation.getIndex();
					currentStation.setIndex(current++);
					//Set the station's new current track's chunk index to 0
					currentStation.getCurrentAudioTrack().setChunkIndex(0);
					//Clear the buffer
					chunks = new LinkedList<TrackChunk>();
				} else {
					//Current station has no more tracks, check if the playlist is set to repeat
					if (currentStation.getRepeat()) {
						currentStation.setIndex(0);
						//Set the station's new current track's chunk index to 0
						currentStation.getCurrentAudioTrack().setChunkIndex(0);
						//Clear the buffer
						chunks = new LinkedList<TrackChunk>();
					}
				}
				state = playWithBufferingState;
			} else if (c.getCommand() == CommandValue.FINISH_STATION) {
				state = finishedState;
				
			} else if (c.getCommand() == CommandValue.RETURN) {
				reset();
				state = quitState;
				
			} else {
				//Any other command is invalid
				throw new UnsupportedOperationException(c.getCommand().toString() + " is not a valid command for " + this.getStateName());
			}
		}

		/**
		 * Gets the name of this state
		 * @return the name of the state as a string
		 */
		@Override
		public String getStateName() {
			return FINISHED_NAME;
		}
	}
}
