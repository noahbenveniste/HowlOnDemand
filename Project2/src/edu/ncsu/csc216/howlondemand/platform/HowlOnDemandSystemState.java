package edu.ncsu.csc216.howlondemand.platform;

import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

public interface HowlOnDemandSystemState {
	/**
	 * Update the {@link HowlOnDemandSystem} based on the given {@link Command}.
	 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
	 * is not a valid action for the given state.  
	 * @param c {@link Command} describing the action that will update the {@link HowlOnDemandSystem}'s
	 * state.
	 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
	 * for the given state.
	 */
	void updateState(Command c);
	
	String getStateName();
}