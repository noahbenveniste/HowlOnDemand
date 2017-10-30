package edu.ncsu.csc216.howlondemand.platform;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

/**
 * Unit tests for the Command class
 * @author Noah Benveniste
 */
public class CommandTest {
	/** Command reference variable to be used throughout testing */
	private Command cmd;
	
	/**
	 * test Command() constructor
	 */
	@Test
	public void testCommand() {
		cmd = new Command(CommandValue.BUFFERING);
		assertEquals(CommandValue.BUFFERING, cmd.getCommand());
	}
	
//	/**
//	 * test getCommand()
//	 */
//	@Test
//	public void testGetCommand() {
//		
//	}
	
	/**
	 * test for valueOf()
	 */
	@Test
	public void testValueOf() {
		cmd = new Command(CommandValue.BUFFERING);
		assertEquals(cmd.getCommand().toString(), CommandValue.valueOf("BUFFERING").toString());
	}
	
	/**
	 * test for values()
	 */
	@Test
	public void testValues() {
		CommandValue[] v1 = CommandValue.values();
		CommandValue[] v2 = new CommandValue[10];
		v2[0] = CommandValue.SELECT_STATION;
		assertEquals(v2[0], v1[0]);
	}
	
	/**
	 * test toString()
	 */
	@Test
	public void testToString() {
		cmd = new Command(CommandValue.BUFFERING);
		assertEquals("BUFFERING", cmd.toString());
	}
}
