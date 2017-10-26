package edu.ncsu.csc216.howlondemand.platform;

import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

/**
 * Wrapper class for the CommandValue enumeration. Allows for
 * greater flexibility when working with enumeration values from
 * CommandValue.
 * @author Noah Benveniste
 */
public class Command {
	/** Stores the enum object */
	private CommandValue c;
	
	/**
	 * Constructs a wrapper for a CommandValue enum
	 * @param c the CommandValue enum object to store in the wrapper
	 */
	public Command(CommandValue c) {
		this.c = c;
	}
	
	/**
	 * Returns the CommandValue enum associated with this wrapper
	 * @return the enum
	 */
	public CommandValue getCommand() {
		return c;
	}
	
	/**
	 * Returns the CommandValue enum value as a string
	 * @return the enum value as a string
	 */
	public String toString() {
		return c.toString();
	}
}
