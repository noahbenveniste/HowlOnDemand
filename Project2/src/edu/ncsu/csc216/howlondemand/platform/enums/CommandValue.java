package edu.ncsu.csc216.howlondemand.platform.enums;

/**
 * An enumeration used by Command objects. Lists the valid values that a
 * Command object can have.
 * @author Noah Benveniste
 */
public enum CommandValue {

	/** Initial State, Quit to Selection*/
	SELECT_STATION, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState to BUFFERING */
	BUFFERING, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState to NOT_BUFFERING */
	NOT_BUFFERING, 
	/** playWithBufferingState, playWithoutBufferingState to STOP */
	STOP, 
	/** selectionState, stopWithBufferingState, stopWithoutBufferingState to PLAY */
	PLAY, 
	/** finishedState to FINISH_TRACK */
	FINISH_TRACK, 
	/** finishedState to FINISH_STATION */
	FINISH_STATION, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState, finishedState to RETURN */
	RETURN, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState to SKIP_FORWARD */
	SKIP_FORWARD, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState to SKIP_BACKWARD */
	SKIP_BACKWARD 
	
}
