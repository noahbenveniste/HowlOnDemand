package edu.ncsu.csc216.howlondemand.platform;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationIOException;
import edu.ncsu.csc216.audioxml.xml.StationXML;
import edu.ncsu.csc216.audioxml.xml.StationsReader;
import edu.ncsu.csc216.howlondemand.model.AudioTrack;
import edu.ncsu.csc216.howlondemand.model.Station;
import edu.ncsu.csc216.howlondemand.model.TrackChunk;

/**
 * 
 * @author Noah Benveniste
 */
public class HowlOnDemandSystem {
	/** */
	public static final int BUFFER_CAPACITY = 100;
	/** */
	public static final int STATION_CAPACITY = 9;
	/** */
	public static final String SELECTION_NAME = "";
	/** */
	public static final String PLAYWITHBUFFERING_NAME = "";
	/** */
	public static final String PLAYWITHOUTBUFFERING_NAME = "";
	/** */
	public static final String STOPWITHBUFFERING_NAME = "";
	/** */
	public static final String STOPWITHOUTBUFFERING_NAME = "";
	/** */
	public static final String QUIT_NAME = "";
	/** */
	public static final String FINISHED_NAME = "";
	
	/** */
	private HowlOnDemandSystemState selectionState;
	/** */
	private HowlOnDemandSystemState playWithBufferingState;
	/** */
	private HowlOnDemandSystemState playWithoutBufferingState;
	/** */
	private HowlOnDemandSystemState stopWithBufferingState;
	/** */
	private HowlOnDemandSystemState stopWithoutBufferingState;
	/** */
	private HowlOnDemandSystemState quitState;
	/** */
	private HowlOnDemandSystemState finishedState;
	/** */
	private HowlOnDemandSystemState state;
	
	/** */
	private static HowlOnDemandSystem singleton;
	/** */
	private Station currentStation;
	/** */
	private ArrayList<Station> stations;
	/** */
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
	 */
	public void loadStationsFromFile(String fileName) throws StationIOException, MalformedTrackException {
		StationsReader stationsReader = new StationsReader(fileName); //Throws StationIOException if there is an issue processing the XML file
		
		//Get a list of StationXML objects from the Stations if the reader read in the objects successfully from the file
		List<StationXML> temp = stationsReader.getStations();
		
		//Attempt to process the StationXML objects and add them to the stations collection
		for (int i = 0; i < temp.size(); i++) {
			try {
				stations.add(new Station(temp.get(i)));
			} catch (MalformedTrackException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Station> getStations() {
		return stations;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void loadStation(Station s) {
		currentStation = s;
	}
	
	/**
	 * 
	 * @return 
	 */
	public Station getCurrentStation() {
		return currentStation;
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
	
	/**
	 * 
	 * @return
	 */
	public int getChunkSize() {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public TrackChunk consumeTrackChunk() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasNextTrackChunk() {
		return false;
	}
	
	/**
	 * 
	 * @param c
	 */
	public void addTrackChunkToBuffer(TrackChunk c) {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean bufferHasRoom() {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public HowlOnDemandSystemState getState() {
		return null;
	}
	
	/**
	 * 
	 * @param c
	 */
	public void executeCommand(Command c) {
		
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 */
	private class SelectionState implements HowlOnDemandSystemState {
		/**
		 * 
		 */
		private SelectionState() {
			
		}
		
		/**
		 * 
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * 
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 */
	private class PlayWithBufferingState implements HowlOnDemandSystemState {
		/**
		 * 
		 */
		private PlayWithBufferingState() {
			
		}
		
		/**
		 * 
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * 
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 */
	private class PlayWithoutBufferingState implements HowlOnDemandSystemState {
		/**
		 * 
		 */
		private PlayWithoutBufferingState() {
			
		}
		
		/**
		 * 
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * 
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 */
	private class StopWithBufferingState implements HowlOnDemandSystemState {
		/**
		 * 
		 */
		private StopWithBufferingState() {
			
		}
		
		/**
		 * 
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * 
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 */
	private class StopWithoutBufferingState implements HowlOnDemandSystemState {
		/**
		 * 
		 */
		private StopWithoutBufferingState() {
			
		}
		
		/**
		 * 
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}
		
		/**
		 * 
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 */
	private class QuitState implements HowlOnDemandSystemState {
		/**
		 * 
		 */
		private QuitState() {
			
		}
		
		/**
		 * 
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * 
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	/**
	 * 
	 * @author Noah Benveniste
	 */
	private class FinishedState implements HowlOnDemandSystemState {
		/**
		 * 
		 */
		private FinishedState() {
			
		}
		
		/**
		 * 
		 */
		@Override
		public void updateState(Command c) {
			// TODO Auto-generated method stub
		}

		/**
		 * 
		 */
		@Override
		public String getStateName() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
