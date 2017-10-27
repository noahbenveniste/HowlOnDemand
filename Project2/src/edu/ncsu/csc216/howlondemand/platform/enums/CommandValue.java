package edu.ncsu.csc216.howlondemand.platform.enums;

/**
 * An enumeration used by Command objects
 * @author Noah Benveniste
 */
public enum CommandValue {

	/** Initial State, Quit -> Selection*/
	SELECT_STATION, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState -> BUFFERING */
	BUFFERING, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState -> NOT_BUFFERING */
	NOT_BUFFERING, 
	/** playWithBufferingState, playWithoutBufferingState -> STOP */
	STOP, 
	/** selectionState, stopWithBufferingState, stopWithoutBufferingState -> PLAY */
	PLAY, 
	/** finishedState -> FINISH_TRACK */
	FINISH_TRACK, 
	/** finishedState -> FINISH_STATION */
	FINISH_STATION, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState, finishedState -> RETURN */
	RETURN, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState -> SKIP_FORWARD */
	SKIP_FORWARD, 
	/** playWithBufferingState, playWithoutBufferingState, stopWithBufferingState, stopWithoutBufferingState -> SKIP_BACKWARD */
	SKIP_BACKWARD 
	
}
