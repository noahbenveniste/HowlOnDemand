package edu.ncsu.csc216.howlondemand.platform;

import java.util.ArrayList;
import java.util.Queue;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationIOException;
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
	private HowlOnDemandSystemState singleton;
	/** */
	private Station currentStation;
	/** */
	private ArrayList<Station> stations;
	/** */
	private Queue chunks;
	
	/**
	 * 
	 * @return
	 */
	public static HowlOnDemandSystem getInstance() {
		return null;
	}
	
	/**
	 * 
	 */
	private HowlOnDemandSystem() {
		
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public void loadStationsFromFile(String fileName) throws StationIOException, MalformedTrackException {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Station> getStations() {
		return null;
	}
	
	/**
	 * 
	 * @param s
	 */
	public void loadStation(Station s) {
		
	}
	
	/**
	 * 
	 * @return 
	 */
	public Station getCurrentStation() {
		return this.currentStation;
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
